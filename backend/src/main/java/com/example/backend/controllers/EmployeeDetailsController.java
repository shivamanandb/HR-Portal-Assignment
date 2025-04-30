package com.example.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.DTOs.EmployeeDetailsDTO;
import com.example.backend.services.EmployeeDetailsService;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin("*")
public class EmployeeDetailsController {

    @Autowired
    private EmployeeDetailsService employeeDetailsService;
    
    @PostMapping("/add-employee")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDetailsDTO employeeDetails){
        return this.employeeDetailsService.addEmployee(employeeDetails);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployee(){
        return this.employeeDetailsService.getAllEmployee();
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<?> updateEmployeeDetails(@PathVariable("empId") Long empId, @RequestBody EmployeeDetailsDTO employeeDetails){
        return this.employeeDetailsService.updateEmployeeDetails(empId, employeeDetails);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<?> deleteEmployeeDetails(@PathVariable("empId") Long empId){
        return this.employeeDetailsService.deleteEmployeeDetails(empId);
    }
}
