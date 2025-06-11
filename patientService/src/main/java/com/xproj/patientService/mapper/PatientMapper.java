package com.xproj.patientService.mapper;

import com.xproj.patientService.dto.PatientRequestDTO;
import com.xproj.patientService.dto.PatientResponseDTO;
import com.xproj.patientService.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toResponseDTO (Patient patient){
         return PatientResponseDTO.builder()
                 .id(patient.getId().toString())
                 .email(patient.getEmail())
                 .address(patient.getAddress())
                 .name(patient.getName())
                 .dateOfBirth(patient.getDateOfBirth().toString())
                 .build();
    }

    public static Patient toModel(PatientRequestDTO patientRequestDTO){
        return  Patient.builder()
                .address(patientRequestDTO.getAddress())
                .name(patientRequestDTO.getName())
                .email(patientRequestDTO.getEmail())
                .registeredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()))
                .dateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()))
                .build();
    }
}
