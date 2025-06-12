package com.xproj.patientService.dto;

import com.xproj.patientService.dto.validationGroups.CreatePatientValidationGroup;
import com.xproj.patientService.model.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PatientRequestDTO {

    //Notnull works for any type, it only ensures the field is not null
    //notBlank works for String type , it ensures the string is not null and contains a meaningfull character

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registration date is required")
    @NotNull(groups = CreatePatientValidationGroup.class,  message = "Registration date cannot be null") // notnull is necessary here as notBlank covers it
    private String registeredDate;

    public Patient updatePatientFromRequest(Patient patient){
        if(this.name != null){
            patient.setName(name);
        }
        if(this.address != null){
            patient.setAddress(address);
        }
        if(this.dateOfBirth != null){
            patient.setDateOfBirth(LocalDate.parse(dateOfBirth));
        }
        // just trying out how this will react with registeredDate excluded in update request dto
        // using the validation groups
        if(this.registeredDate != null){
            patient.setRegisteredDate(LocalDate.parse(registeredDate));
        }

        return patient;
    }
}
