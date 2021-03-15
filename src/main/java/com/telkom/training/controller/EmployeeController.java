package com.telkom.training.controller;

import com.telkom.training.model.EmployeeModel;
import com.telkom.training.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/api/v1/employee", produces={"application/json"})
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Iterable<EmployeeModel> getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeModel createEmployee(@RequestBody EmployeeModel employeeModel){
        return employeeService.createEmployee(employeeModel);
    }
    
    @GetMapping("/{id}")
    public EmployeeModel getEmployee(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/name/{name}")
    public EmployeeModel getEmployeeByName(@PathVariable String name){
        return employeeService.getEmployeeByName(name);
    }

    @GetMapping("/address/{address}")
    public Iterable<EmployeeModel> getEmployeeByAddress(@PathVariable String address) {
        return employeeService.getEmployeesByAddr(address);
    }


    @GetMapping("/findByNameAndAddress")
    public EmployeeModel getEmployeeByNameAndAge(@RequestParam String name, @RequestParam String address){
        return employeeService.getEmployeeByNameAndAddr(name, address);
    }
}
