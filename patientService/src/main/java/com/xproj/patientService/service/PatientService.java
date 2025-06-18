package com.xproj.patientService.service;

import com.xproj.patientService.dto.PatientRequestDTO;
import com.xproj.patientService.dto.PatientResponseDTO;
import com.xproj.patientService.exception.EmailAlreadyExistException;
import com.xproj.patientService.exception.EmailCannotBeChangedException;
import com.xproj.patientService.exception.PatientNotfoundException;
import com.xproj.patientService.grpc.BillingServiceGrpcClient;
import com.xproj.patientService.mapper.PatientMapper;
import com.xproj.patientService.model.Patient;
import com.xproj.patientService.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toResponseDTO).collect(Collectors.toList());
    }

    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO){

        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("Email already exist");
        }

        Patient patient = PatientMapper.toModel(patientRequestDTO);
        Patient savedPatient = patientRepository.save(patient);

        billingServiceGrpcClient.createBillingAccount(savedPatient.getId().toString(),
                savedPatient.getName(), savedPatient.getEmail());
        return PatientMapper.toResponseDTO(savedPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO requestDTO){

        Patient patient = patientRepository.findById(id).
                orElseThrow(()-> new PatientNotfoundException("Patient not found"));

        if (!patient.getEmail().equals(requestDTO.getEmail())){
            throw new EmailCannotBeChangedException("Email cannot be changed, please contact support");
        }

        Patient updatedPatient = requestDTO.updatePatientFromRequest(patient);
        Patient savedPatient = patientRepository.save(updatedPatient);
        return PatientMapper.toResponseDTO(savedPatient);

    }

    public void deletePatient(UUID id){
        Patient patient = patientRepository.findById(id).
                orElseThrow(()-> new PatientNotfoundException("Patient not found"));
        patientRepository.delete(patient);
    }


}
