package com.telkom.training.repository;

import com.telkom.training.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String>{

    UserModel getUserByEmail(String email);
    
}
