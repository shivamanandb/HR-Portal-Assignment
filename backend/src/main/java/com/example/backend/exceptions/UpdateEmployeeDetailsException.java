package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class UpdateEmployeeDetailsException extends RuntimeException{
    
    private HttpStatus status;

    public UpdateEmployeeDetailsException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
