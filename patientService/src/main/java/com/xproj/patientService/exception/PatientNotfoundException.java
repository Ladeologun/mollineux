package com.xproj.patientService.exception;

public class PatientNotfoundException extends RuntimeException{
    public PatientNotfoundException(String message){
        super(message);
    }
}
