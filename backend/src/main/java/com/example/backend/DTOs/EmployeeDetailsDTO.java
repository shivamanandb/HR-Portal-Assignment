package com.example.backend.DTOs;

public class EmployeeDetailsDTO {
    
    private String name;
    private String department;
    private String email;
    private String salary;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }

    public EmployeeDetailsDTO(String name, String department, String email, String salary) {
        this.name = name;
        this.department = department;
        this.email = email;
        this.salary = salary;
    }

    public EmployeeDetailsDTO(){
        
    }

}
