package com.telkom.training.service;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.telkom.training.dto.UserDTO;
import com.telkom.training.model.UserModel;
import com.telkom.training.pkg.response.APIResponse;
import com.telkom.training.pkg.utils.ResponseMessage;
import com.telkom.training.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public UserModel createUser(UserModel userModel){
        return userRepository.save(userModel);
    }

    public APIResponse<Iterable<UserDTO>> getAllUsers() {
        APIResponse<Iterable<UserDTO>> listUserResponse = new APIResponse<>();
        listUserResponse.setCode(HttpStatus.OK.value());
        listUserResponse.setHttpStatus(HttpStatus.OK);
        listUserResponse.setMessage(ResponseMessage.MessageSuccess);
        // listUserResponse.setData(userRepository.findAll(Sort.by(Order.asc("username"))));
        listUserResponse.setData(userRepository.findAll().stream().map(this::convertUserDTOMapper).collect(Collectors.toList()));
        return listUserResponse;
    }

    public APIResponse<UserDTO> getUserById(String id){
        APIResponse<UserDTO> employeeResponse = new APIResponse<>();
        Optional<UserModel> currentEmployee = userRepository.findById(id);
        if (currentEmployee.isPresent()) {
            employeeResponse.setCode(HttpStatus.OK.value());
            employeeResponse.setHttpStatus(HttpStatus.OK);
            employeeResponse.setMessage(ResponseMessage.MessageSuccess);
            employeeResponse.setData(currentEmployee.map(this::convertUserDTOMapper).get());
        } else {
            employeeResponse.setCode(HttpStatus.NOT_FOUND.value());
            employeeResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            employeeResponse.setMessage(ResponseMessage.MessageFailed);
        }
        return employeeResponse;
    }

    private UserDTO convertUserDTOMapper(UserModel userModel){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDTO userDTO = modelMapper.map(userModel, UserDTO.class);
        return userDTO;
    }

    public void deleteUserCRUD() {
        userRepository.deleteAll();
    }

    public void deleteUserJPA() {
        // userRepository.deleteAllBatch()
    }

    public Page<UserModel> getUserByPage(int page, int size){
        Pageable pagination = PageRequest.of(page, size);
        return userRepository.findAll(pagination);
    }

}
