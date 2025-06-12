package com.xproj.patientService.exception;

public class EmailCannotBeChangedException extends RuntimeException{
    public EmailCannotBeChangedException(String message) {
        super(message);
    }
}
