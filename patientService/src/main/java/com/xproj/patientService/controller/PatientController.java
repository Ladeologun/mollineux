package com.xproj.patientService.controller;


import com.xproj.patientService.dto.PatientRequestDTO;
import com.xproj.patientService.dto.PatientResponseDTO;
import com.xproj.patientService.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> fetchAllPatients(){
        List<PatientResponseDTO> allPatients = patientService.gePatients();
        return ResponseEntity.ok().body(allPatients);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> addPatient(
            @Valid @RequestBody PatientRequestDTO patientRequestDTO){

       PatientResponseDTO patientResponseDTO = patientService.addPatient(patientRequestDTO);
       return ResponseEntity.ok().body(patientResponseDTO);
    }
}
