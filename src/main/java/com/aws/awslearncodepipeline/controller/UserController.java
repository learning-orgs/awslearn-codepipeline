package com.aws.awslearncodepipeline.controller;

import com.aws.awslearncodepipeline.model.Users;
import com.aws.awslearncodepipeline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/register")
    public Users register(@RequestBody Users user) throws IOException {
        return service.register(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        System.out.println("REQUEST: " + user);
        return service.verify(user);
    }
}