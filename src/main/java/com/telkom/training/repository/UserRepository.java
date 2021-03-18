package com.telkom.training.repository;

import com.telkom.training.model.UserModel;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, String>{
    
}
