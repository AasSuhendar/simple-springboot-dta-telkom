package com.telkom.training.service;

import com.telkom.training.model.EmployeeModel;
import com.telkom.training.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeModel createEmployee(EmployeeModel employeeModel){
        return employeeRepository.save(employeeModel);
    }

    public EmployeeModel getEmployeeById(int id){
        return employeeRepository.findById(id).get();
    }

    public EmployeeModel getEmployeeByName(String name){
        return employeeRepository.getByEmployeeName(name);
    }

    public EmployeeModel getEmployeeByNameAndAddr(String name, String address){
        return employeeRepository.getByEmployeeNameAndEmployeeAddress(name, address);
    }

    public Iterable<EmployeeModel> getEmployeesByAddr(String address) {
        return employeeRepository.getByEmployeeAddress(address);
    }
}
