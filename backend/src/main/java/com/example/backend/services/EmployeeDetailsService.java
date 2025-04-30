package com.example.backend.services;

import org.springframework.http.ResponseEntity;

import com.example.backend.DTOs.EmployeeDetailsDTO;

public interface EmployeeDetailsService {

    ResponseEntity<?> addEmployee(EmployeeDetailsDTO employeeDetails);

    ResponseEntity<?> getAllEmployee();

    ResponseEntity<?> updateEmployeeDetails(Long empId, EmployeeDetailsDTO employeeDetails);

    ResponseEntity<?> deleteEmployeeDetails(Long empId);
    
}
