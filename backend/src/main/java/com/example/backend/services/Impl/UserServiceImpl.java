package com.example.backend.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.DTOs.AuthenticationDTO;
import com.example.backend.DTOs.UserDetailsDTO;
import com.example.backend.exceptions.AddEmployeeDetailsException;
import com.example.backend.exceptions.AddUserException;
import com.example.backend.exceptions.AuthException;
import com.example.backend.models.EmployeeDetails;
import com.example.backend.models.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> addUser(UserDetailsDTO userDetails) {
        
        // validation
        if(userDetails.getName()==null || userDetails.getEmail()==null || userDetails.getPassword()==null){
            throw new AddUserException("Please fill up Required Fields", HttpStatus.BAD_REQUEST);
        }

        // check whether the email is valid or not
        if(!userDetails.getEmail().endsWith("@gmail.com")){
            throw new AddEmployeeDetailsException("Email is not valid", HttpStatus.BAD_REQUEST);
        }

        // Checking for the duplication of email id
        User user = this.userRepository.findByEmail(userDetails.getEmail());
        if(user!=null){
            throw new AddUserException("Email already exists", HttpStatus.CONFLICT);
        }

        // saving user data
        user = this.userRepository.save(new User(userDetails.getName(), userDetails.getPassword(), userDetails.getEmail()));

        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> authenticateDetails(AuthenticationDTO authenticationDetails) {
        
        if(authenticationDetails.getEmail()==null){
            throw new AuthException("email can't be null", HttpStatus.BAD_REQUEST);
        }
        
        if(authenticationDetails.getPassword()==null){
            throw new AuthException("password can't be null", HttpStatus.BAD_REQUEST);
        }

        User user = this.userRepository.findByEmail(authenticationDetails.getEmail());

        if(user==null){
            throw new AuthException("Invalid email", HttpStatus.BAD_REQUEST);
        }

        if(!user.getPassword().equals(authenticationDetails.getPassword())){
            throw new AuthException("Invalid password", HttpStatus.BAD_REQUEST);
        }
        
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    

}
