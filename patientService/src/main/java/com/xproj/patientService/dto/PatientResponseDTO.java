package com.xproj.patientService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientResponseDTO {

    private String id;
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;
}
