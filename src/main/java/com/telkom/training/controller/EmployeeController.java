package com.telkom.training.controller;

import com.telkom.training.model.EmployeeModel;
import com.telkom.training.pkg.response.APIResponse;
import com.telkom.training.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PutMapping;


@RequestMapping(value="/api/v1/employee", produces={"application/json"})
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<EmployeeModel>>> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    @PostMapping
    public EmployeeModel createEmployee(@RequestBody EmployeeModel employeeModel){
        return employeeService.createEmployee(employeeModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<EmployeeModel>> getEmployee(@PathVariable int id){
        ResponseEntity<APIResponse<EmployeeModel>> response;
        if (employeeService.getEmployeeById(id).getHttpStatus().equals(HttpStatus.OK)) {
            response = new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<APIResponse<EmployeeModel>> getEmployeeByName(@PathVariable String name){
        // return employeeService.getEmployeeByName(name);
        ResponseEntity<APIResponse<EmployeeModel>> response;
        if (employeeService.getEmployeeByName(name).getHttpStatus().equals(HttpStatus.OK)) {
            response = new ResponseEntity<>(employeeService.getEmployeeByName(name), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(employeeService.getEmployeeByName(name), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<APIResponse<Iterable<EmployeeModel>>> getEmployeeByAddress(@PathVariable String address) {
        return new ResponseEntity<>(employeeService.getEmployeesByAddr(address), HttpStatus.OK);
    }

    @GetMapping("/findByNameAndAddress")
    public EmployeeModel getEmployeeByNameAndAge(@RequestParam String name, @RequestParam String address){
        return employeeService.getEmployeeByNameAndAddr(name, address);
    }

    @PutMapping("/update/{id}")
    public EmployeeModel updateEmployee(@PathVariable int id, @RequestBody EmployeeModel employee) {
        //TODO: process PUT request
        return employeeService.putEmployee(employee, id);
    }

    @PatchMapping("/patch/{id}")
    public EmployeeModel patchEmployee(@PathVariable int id, @RequestBody EmployeeModel employee) {
        //TODO: process PUT request
        return employeeService.patchEmployee(employee, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public EmployeeModel deleteEmployee(@PathVariable int id) {
        //TODO: process PUT request
        return employeeService.deleteEmployee(id);
    }

    @PostMapping(value="/uploadfile")
    public boolean uploadfile(@RequestParam("file") MultipartFile file) {
        //TODO: process POST request
        return employeeService.saveFile(file);
    }
    
}
