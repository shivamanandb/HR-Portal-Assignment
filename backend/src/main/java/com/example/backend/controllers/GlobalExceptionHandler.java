package com.example.backend.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.backend.exceptions.AddEmployeeDetailsException;
import com.example.backend.exceptions.AddUserException;
import com.example.backend.exceptions.AuthException;
import com.example.backend.exceptions.DeleteEmployeeDetailsException;
import com.example.backend.exceptions.UpdateEmployeeDetailsException;
import com.example.backend.models.ErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(AddUserException.class)
    public ResponseEntity<?> handleUserNotAddedException(AddUserException exception){
        ErrorResponse addUserErrorResponse = new ErrorResponse(LocalDateTime.now(), "Error: " + exception.getMessage(), "Enter Valid Details");
        return new ResponseEntity<>(addUserErrorResponse, exception.getStatus());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handleAuthException(AuthException exception){
        ErrorResponse authErrorResponse = new ErrorResponse(LocalDateTime.now(),"Error: " + exception.getMessage(), "Enter Valid Details");
        return new ResponseEntity<>(authErrorResponse, exception.getStatus());
    }

    @ExceptionHandler(AddEmployeeDetailsException.class)
    public ResponseEntity<?> handleAddEmployeeDetailsException(AddEmployeeDetailsException exception){
        ErrorResponse employeeDetailsErrorResponse = new ErrorResponse(LocalDateTime.now(), "Error: " + exception.getMessage(), "");
        return new ResponseEntity<>(employeeDetailsErrorResponse, exception.getStatus());
    }

    @ExceptionHandler(UpdateEmployeeDetailsException.class)
    public ResponseEntity<?> handleUpdateEmloyeeDetailsException(UpdateEmployeeDetailsException exception){
        ErrorResponse updateEmployeeDetailsResponse = new ErrorResponse(LocalDateTime.now(), "Error: " + exception.getMessage(), "");
        return new ResponseEntity<>(updateEmployeeDetailsResponse, exception.getStatus());
    }

    @ExceptionHandler(DeleteEmployeeDetailsException.class)
    public ResponseEntity<?> handleDeleteEmloyeeDetailsException(DeleteEmployeeDetailsException exception){
        ErrorResponse deleteEmployeeDetailsResponse = new ErrorResponse(LocalDateTime.now(), "Error: " + exception.getMessage(), "");
        return new ResponseEntity<>(deleteEmployeeDetailsResponse, exception.getStatus());
    }
}