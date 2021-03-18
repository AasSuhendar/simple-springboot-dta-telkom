package com.telkom.training.service;

import java.util.Optional;

import com.telkom.training.dto.UserDTO;
import com.telkom.training.model.UserModel;
import com.telkom.training.pkg.response.APIResponse;
import com.telkom.training.pkg.utils.ResponseMessage;
import com.telkom.training.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public UserModel createUser(UserModel userModel){
        return userRepository.save(userModel);
    }

    public APIResponse<Iterable<UserModel>> getAllUsers() {
        APIResponse<Iterable<UserModel>> listUserResponse = new APIResponse<>();
        listUserResponse.setCode(HttpStatus.OK.value());
        listUserResponse.setHttpStatus(HttpStatus.OK);
        listUserResponse.setMessage(ResponseMessage.MessageSuccess);
        listUserResponse.setData(userRepository.findAll());
        return listUserResponse;
    }

    public APIResponse<UserDTO> getUserById(String id){
        APIResponse<UserDTO> employeeResponse = new APIResponse<>();
        Optional<UserModel> currentEmployee = userRepository.findById(id);
        if (currentEmployee.isPresent()) {
            employeeResponse.setCode(HttpStatus.OK.value());
            employeeResponse.setHttpStatus(HttpStatus.OK);
            employeeResponse.setMessage(ResponseMessage.MessageSuccess);
            // employeeResponse.setData(currentEmployee.get());
        } else {
            employeeResponse.setCode(HttpStatus.NOT_FOUND.value());
            employeeResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            employeeResponse.setMessage(ResponseMessage.MessageFailed);
        }
        return employeeResponse;
    }

}
