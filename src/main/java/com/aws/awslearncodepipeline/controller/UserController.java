package com.aws.awslearncodepipeline.controller;

import com.aws.awslearncodepipeline.model.Users;
import com.aws.awslearncodepipeline.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User Management API", description = "Endpoints for managing user registration and login")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Operation(summary = "Register a new user", description = "Allows a new user to register by providing necessary details.")
    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user) throws IOException {
        LOGGER.info("Register request received for user: {}", user.getUsername());
        Users registeredUser = service.register(user);
        LOGGER.info("User registered successfully: {}", registeredUser.getUsername());
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED); // Return 201 Created status
    }

    @Operation(summary = "Login user", description = "Authenticates a user and returns a JWT token.")
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody Users user) {
        LOGGER.info("Login request received for user: {}", user.getUsername());

        // Call the service to authenticate the user and generate the JWT token
        String jwtToken = service.verify(user);

        // Check if authentication was successful
        if (!jwtToken.equals("fail")) {
            LOGGER.info("Login successful for user: {}", user.getUsername());

            // Return the token in a JSON response
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtToken);

            LOGGER.debug("JWT Token generated for user {}: {}", user.getUsername(), jwtToken);
            return ResponseEntity.ok(response); // Return 200 OK
        } else {
            LOGGER.warn("Login failed for user: {}", user.getUsername());
            // Return 401 Unauthorized status
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

//    @Operation(summary = "Logged in user csrf ", description = "Returns csrf token.")
//    @GetMapping("/csrf-token")
//    public CsrfToken getCsrfToken(HttpServletRequest request) {
//        return (CsrfToken) request.getAttribute("_csrf");
//    }


}
