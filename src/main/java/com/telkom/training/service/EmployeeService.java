package com.telkom.training.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.telkom.training.model.EmployeeModel;
import com.telkom.training.pkg.response.APIResponse;
import com.telkom.training.pkg.response.employee.EmployeeResponse;
import com.telkom.training.pkg.response.employee.ListEmployeeResponse;
import com.telkom.training.pkg.utils.ResponseMessage;
import com.telkom.training.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeService {
    
    
    private EmployeeRepository employeeRepository;

    private final Path folderUpload = Paths.get("uploads");

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    public APIResponse<Iterable<EmployeeModel>> getAllEmployees() {
        APIResponse<Iterable<EmployeeModel>> listEmployeeResponse = new APIResponse<>();
        listEmployeeResponse.setCode(HttpStatus.OK.value());
        listEmployeeResponse.setHttpStatus(HttpStatus.OK);
        listEmployeeResponse.setMessage(ResponseMessage.MessageSuccess);
        listEmployeeResponse.setData(employeeRepository.findAll());
        return listEmployeeResponse;
    }

    public ListEmployeeResponse getAllEmployeesOld() {
        ListEmployeeResponse listEmployeeResponse = new ListEmployeeResponse();
        listEmployeeResponse.setCode(HttpStatus.OK.value());
        listEmployeeResponse.setMessage(ResponseMessage.MessageSuccess);
        listEmployeeResponse.setListEmployee(employeeRepository.findAll());
        return listEmployeeResponse;
    }

    public EmployeeModel createEmployee(EmployeeModel employeeModel){
        return employeeRepository.save(employeeModel);
    }
    
    public APIResponse<EmployeeModel> getEmployeeById(int id){
        APIResponse<EmployeeModel> employeeResponse = new APIResponse<>();
        Optional<EmployeeModel> currentEmployee = employeeRepository.findById(id);
        if (currentEmployee.isPresent()) {
            employeeResponse.setCode(HttpStatus.OK.value());
            employeeResponse.setHttpStatus(HttpStatus.OK);
            employeeResponse.setMessage(ResponseMessage.MessageSuccess);
            employeeResponse.setData(currentEmployee.get());
        } else {
            employeeResponse.setCode(HttpStatus.NOT_FOUND.value());
            employeeResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            employeeResponse.setMessage(ResponseMessage.MessageFailed);
        }
        return employeeResponse;
    }

    public EmployeeResponse getEmployeeByIdOld(int id){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        Optional<EmployeeModel> currentEmployee = employeeRepository.findById(id);
        if (currentEmployee.isPresent()) {
            employeeResponse.setCode(HttpStatus.OK.value());
            employeeResponse.setHttpStatus(HttpStatus.OK);
            employeeResponse.setMessage(ResponseMessage.MessageSuccess);
            employeeResponse.setEmployee(currentEmployee.get());
        } else {
            employeeResponse.setCode(HttpStatus.NOT_FOUND.value());
            employeeResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            employeeResponse.setMessage(ResponseMessage.MessageFailed);
        }
        return employeeResponse;
    }

    public APIResponse<EmployeeModel> getEmployeeByName(String name){
        APIResponse<EmployeeModel> employeeResponse = new APIResponse<>();
        Optional<EmployeeModel> currentEmployee = employeeRepository.getByEmployeeName(name);
        if (currentEmployee.isPresent()) {
            employeeResponse.setCode(HttpStatus.OK.value());
            employeeResponse.setHttpStatus(HttpStatus.OK);
            employeeResponse.setMessage(ResponseMessage.MessageSuccess);
            employeeResponse.setData(currentEmployee.get());
        } else {
            employeeResponse.setCode(HttpStatus.NOT_FOUND.value());
            employeeResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            employeeResponse.setMessage(ResponseMessage.MessageFailed);
        }
        return employeeResponse;
    }

    public EmployeeResponse getEmployeeByNameOld(String name){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        Optional<EmployeeModel> currentEmployee = employeeRepository.getByEmployeeName(name);
        if (currentEmployee.isPresent()) {
            employeeResponse.setCode(HttpStatus.OK.value());
            employeeResponse.setHttpStatus(HttpStatus.OK);
            employeeResponse.setMessage(ResponseMessage.MessageSuccess);
            employeeResponse.setEmployee(currentEmployee.get());
        } else {
            employeeResponse.setCode(HttpStatus.NOT_FOUND.value());
            employeeResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            employeeResponse.setMessage(ResponseMessage.MessageFailed);
        }
        return employeeResponse;
    }

    public EmployeeModel getEmployeeByNameAndAddr(String name, String address){
        return employeeRepository.getByEmployeeNameAndEmployeeAddress(name, address);
    }

    public APIResponse<Iterable<EmployeeModel>> getEmployeesByAddr(String address) {
        APIResponse<Iterable<EmployeeModel>> listEmployeeResponse = new APIResponse<>();
        listEmployeeResponse.setCode(HttpStatus.OK.value());
        listEmployeeResponse.setHttpStatus(HttpStatus.OK);
        listEmployeeResponse.setMessage(ResponseMessage.MessageSuccess);
        listEmployeeResponse.setData(employeeRepository.getByEmployeeAddress(address));
        return listEmployeeResponse;
    }   

    public EmployeeModel putEmployee(EmployeeModel employeeModel, int id){
        Optional<EmployeeModel> currentEmployee = employeeRepository.findById(id);
        if (currentEmployee.isPresent()) {
            return employeeRepository.save(employeeModel);
        } else {
            return null;
        }
    }

    public EmployeeModel patchEmployee(EmployeeModel employeeModel, int id){
        Optional<EmployeeModel> currentEmployee = employeeRepository.findById(id);
        if (currentEmployee.isPresent()) {
            EmployeeModel currEmployee = currentEmployee.get();

            if (employeeModel.getEmployeeName() != null) {
                currEmployee.setEmployeeName(employeeModel.getEmployeeName());
            }
            
            if (employeeModel.getEmployeeEmail() != null) {
                currEmployee.setEmployeeEmail(employeeModel.getEmployeeEmail());
            } 
            
            if (employeeModel.getEmployeeAge() != 0) {
                currEmployee.setEmployeeAge(employeeModel.getEmployeeAge());
            } 
            
            if (employeeModel.getEmployeeAddress() != null) {
                currEmployee.setEmployeeAddress(employeeModel.getEmployeeAddress());
            }

            return employeeRepository.save(currEmployee);
        } else {
            return null;
        }
    }

    public EmployeeModel deleteEmployee( int id){
        Optional<EmployeeModel> currentEmployee = employeeRepository.findById(id);
        if (currentEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return currentEmployee.get();
        } else {
            return null;
        }
    }

    public boolean saveFile(MultipartFile file) {
        try {
            if (!Files.exists(folderUpload)) {
                Files.createDirectories(folderUpload);
            }

            Files.copy(file.getInputStream(), this.folderUpload.resolve(file.getOriginalFilename()));
            return true;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

}
