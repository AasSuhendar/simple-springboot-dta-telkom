package com.telkom.training.repository;

import java.util.Optional;

import com.telkom.training.model.EmployeeModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends CrudRepository<EmployeeModel, Integer>{

    Optional<EmployeeModel> getByEmployeeName(String name);
    EmployeeModel getByEmployeeNameAndEmployeeAddress(String name, String address);
    Iterable<EmployeeModel> getByEmployeeAddress(String address);
    
    @Transactional
    void deleteByEmployeeName(String name);
    
}
