package com.telkom.training.controller;

import com.telkom.training.model.UserModel;
import com.telkom.training.pkg.response.APIResponse;
import com.telkom.training.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping(value="/api/v1/user", produces={"application/json"})
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<APIResponse<Iterable<UserModel>>> getAllEmployee() {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }
    
}
