package com.aws.awslearncodepipeline.service;

import com.aws.awslearncodepipeline.model.Users;
import com.aws.awslearncodepipeline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository repo;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) throws IOException {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.addUser(user);
        return user;
    }

    public String verify(Users user) {
        System.out.println("Verifying user: " + user);
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            System.out.println("Is authenticated: " + authentication.isAuthenticated());
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            } else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

    }

}