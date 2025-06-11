package com.xproj.patientService.service;

import com.xproj.patientService.dto.PatientRequestDTO;
import com.xproj.patientService.dto.PatientResponseDTO;
import com.xproj.patientService.exception.EmailAlreadyExistException;
import com.xproj.patientService.mapper.PatientMapper;
import com.xproj.patientService.model.Patient;
import com.xproj.patientService.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<PatientResponseDTO> gePatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toResponseDTO).collect(Collectors.toList());
    }

    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO){

        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("Email already exist");
        }

        Patient patient = PatientMapper.toModel(patientRequestDTO);
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.toResponseDTO(savedPatient);
    }


}
