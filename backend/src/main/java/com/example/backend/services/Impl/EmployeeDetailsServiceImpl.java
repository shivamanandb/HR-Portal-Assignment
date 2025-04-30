package com.example.backend.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.DTOs.EmployeeDetailsDTO;
import com.example.backend.exceptions.AddEmployeeDetailsException;
import com.example.backend.exceptions.DeleteEmployeeDetailsException;
import com.example.backend.exceptions.UpdateEmployeeDetailsException;
import com.example.backend.models.EmployeeDetails;
import com.example.backend.repository.EmployeeDetailsRepository;
import com.example.backend.services.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    @Override
    public ResponseEntity<?> addEmployee(EmployeeDetailsDTO employeeDetails) {
        
        if(employeeDetails.getName()==null || employeeDetails.getName().length()==0){
            throw new AddEmployeeDetailsException("Name can't be empty", HttpStatus.BAD_REQUEST);
        }else if(employeeDetails.getDepartment()==null || employeeDetails.getDepartment().length()==0){
            throw new AddEmployeeDetailsException("Department can't be empty", HttpStatus.BAD_REQUEST);
        }else if(employeeDetails.getEmail()==null || employeeDetails.getEmail().length()==0){
            throw new AddEmployeeDetailsException("Email can't be empty", HttpStatus.BAD_REQUEST);
        }else if(employeeDetails.getSalary()==null || employeeDetails.getSalary().length()==0){
            throw new AddEmployeeDetailsException("Salary can't be empty", HttpStatus.BAD_REQUEST);
        }

        // Checking for the duplication of email id
        EmployeeDetails employee = this.employeeDetailsRepository.findByEmail(employeeDetails.getEmail());
        if(employee!=null){
            throw new AddEmployeeDetailsException("Email already exists", HttpStatus.CONFLICT);
        }

        // check whether the email is valid or not
        if(!employeeDetails.getEmail().endsWith("@gmail.com")){
            throw new AddEmployeeDetailsException("Email is not valid", HttpStatus.BAD_REQUEST);
        }

        // check whether the salary is valid or not using regex
        boolean isnumeric = employeeDetails.getSalary().matches("^\\d+(\\.\\d+)?$");

        if(!isnumeric){
            throw new AddEmployeeDetailsException("Salary is not valid", HttpStatus.BAD_REQUEST);
        }

        // Stores Employee Details into EmployeeDetails Table
        this.employeeDetailsRepository.save(
            new EmployeeDetails(
                employeeDetails.getName(),
                employeeDetails.getDepartment(),
                employeeDetails.getEmail(),
                employeeDetails.getSalary()
            )
        );
        return new ResponseEntity<>("Employee Details added Successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAllEmployee() {
        
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        try {
            employeeDetails = this.employeeDetailsRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error: EmployeeDetials couldn't fetched");
        }
        return new ResponseEntity<>(employeeDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateEmployeeDetails(Long empId, EmployeeDetailsDTO employeeDetails) {
        
        // Validate fields (same as before)
        if(employeeDetails.getName() == null || employeeDetails.getName().length()==0){
            throw new AddEmployeeDetailsException("Name can't be empty", HttpStatus.BAD_REQUEST);
        } else if(employeeDetails.getDepartment() == null || employeeDetails.getDepartment().length()==0){
            throw new AddEmployeeDetailsException("Department can't be empty", HttpStatus.BAD_REQUEST);
        } else if(employeeDetails.getEmail() == null || employeeDetails.getEmail().length()==0){
            throw new AddEmployeeDetailsException("Email can't be empty", HttpStatus.BAD_REQUEST);
        } else if(employeeDetails.getSalary() == null || employeeDetails.getSalary().length()==0){
            throw new AddEmployeeDetailsException("Salary can't be empty", HttpStatus.BAD_REQUEST);
        }
    
        // Validate email
        if(!employeeDetails.getEmail().endsWith("@gmail.com")){
            throw new AddEmployeeDetailsException("Email is not valid", HttpStatus.BAD_REQUEST);
        }
    
        // Validate salary with regex
        boolean isnumeric = employeeDetails.getSalary().matches("^\\d+(\\.\\d+)?$");
        if(!isnumeric){
            throw new UpdateEmployeeDetailsException("Salary is not valid", HttpStatus.BAD_REQUEST);
        }
    
        // Check if employee with given email exists
        EmployeeDetails details = employeeDetailsRepository.findById(empId)
                                  .orElseThrow(() -> new UpdateEmployeeDetailsException("Employee Details not found", HttpStatus.BAD_REQUEST)); 

        // check whether email is replaced by another one or not
        if(!employeeDetails.getEmail().equals(details.getEmail())){
            if(this.employeeDetailsRepository.findByEmail(employeeDetails.getEmail())!=null){
                throw new UpdateEmployeeDetailsException("Email already exist", HttpStatus.CONFLICT);
            }
        }
    
        // Update existing employee
        details.setName(employeeDetails.getName());
        details.setDepartment(employeeDetails.getDepartment());
        details.setSalary(employeeDetails.getSalary());
        details.setEmail(employeeDetails.getEmail());
    
        employeeDetailsRepository.save(details);
    
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> deleteEmployeeDetails(Long empId) {
        
        //validate employee ID
        if(empId==null){
            throw new DeleteEmployeeDetailsException("Employee Id must not be null", HttpStatus.BAD_REQUEST);
        }
        
        EmployeeDetails employeeDetails = this.employeeDetailsRepository.findById(empId)
                                          .orElseThrow(()-> new DeleteEmployeeDetailsException("Employee Details not found", HttpStatus.BAD_REQUEST));      
        
        this.employeeDetailsRepository.deleteById(empId);
        return new ResponseEntity<>("Employee Removed Successfully", HttpStatus.OK);
    }
}
