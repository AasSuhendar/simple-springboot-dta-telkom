package com.telkom.training.repository;

import java.util.Optional;

import com.telkom.training.model.EmployeeModel;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeModel, Integer>{

    Optional<EmployeeModel> getByEmployeeName(String name);
    EmployeeModel getByEmployeeNameAndEmployeeAddress(String name, String address);
    Iterable<EmployeeModel> getByEmployeeAddress(String address);
    void deleteByEmployeeName(String name);
    
}
