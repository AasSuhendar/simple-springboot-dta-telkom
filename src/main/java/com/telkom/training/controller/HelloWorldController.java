package com.telkom.training.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    

    @GetMapping("/hello")
    public String HelloWorld() {
        return "Hello World";
    }

    @GetMapping("/login")
    public String Login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return email+" "+password;
    }

    // @GetMapping("/insert-employee")
    // public String InsertEmployee(@RequestBody(String username username)){
    //     return "Employee inserted "+username;
    // }
}
