package com.xproj.patientService.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex
    ){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleEmailExistException(
            EmailAlreadyExistException ex
    ){
        Map<String, String> errors = new HashMap<>();
        errors.put("status","failure");
        errors.put("message", ex.getMessage());
        log.warn(ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotfoundException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotfoundException(
            PatientNotfoundException ex
    ){
        Map<String, String> errors = new HashMap<>();
        errors.put("status","failure");
        errors.put("message", ex.getMessage());
        log.warn(ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailCannotBeChangedException.class)
    public ResponseEntity<Map<String, String>> handleEmailCannotBeChangedException(
            EmailCannotBeChangedException ex
    ){
        Map<String, String> errors = new HashMap<>();
        errors.put("status","failure");
        errors.put("message", ex.getMessage());
        log.warn(ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

}
