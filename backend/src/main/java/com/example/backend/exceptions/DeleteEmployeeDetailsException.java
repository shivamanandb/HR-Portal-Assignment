package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class DeleteEmployeeDetailsException extends RuntimeException {
    
    private HttpStatus status;

    public DeleteEmployeeDetailsException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
