package com.example.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.DTOs.AuthenticationDTO;
import com.example.backend.DTOs.UserDetailsDTO;
import com.example.backend.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody UserDetailsDTO userDetails) {
        return this.userService.addUser(userDetails);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateDetails(@RequestBody AuthenticationDTO authenticationDetails){
        return this.userService.authenticateDetails(authenticationDetails);
    }
}
