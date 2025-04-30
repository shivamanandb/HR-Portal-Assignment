package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;

public class AddEmployeeDetailsException extends RuntimeException {

    private HttpStatus status;
    
    public AddEmployeeDetailsException(String message, HttpStatus status){

        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
