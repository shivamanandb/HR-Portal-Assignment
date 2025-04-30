package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.models.EmployeeDetails;

public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long>{

    @Query(value = "Select emp from EmployeeDetails emp where email = :email")
    EmployeeDetails findByEmail(String email);

    @Query(value = "Select emp from EmployeeDetails emp")
    List<EmployeeDetails> findAll();
}
