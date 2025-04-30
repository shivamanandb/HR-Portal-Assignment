package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query(value = "Select u from User u where email = :email")
    public User findByEmail(String email);

}
