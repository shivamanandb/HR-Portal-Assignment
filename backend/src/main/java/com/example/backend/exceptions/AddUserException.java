package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class AddUserException extends RuntimeException {
    
    private HttpStatus status;

    public AddUserException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
