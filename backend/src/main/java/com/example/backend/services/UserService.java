package com.example.backend.services;

import org.springframework.http.ResponseEntity;

import com.example.backend.DTOs.AuthenticationDTO;
import com.example.backend.DTOs.UserDetailsDTO;

public interface UserService {

    ResponseEntity<?> addUser(UserDetailsDTO userDetails);

    ResponseEntity<?> authenticateDetails(AuthenticationDTO authenticationDetails);
    
}
