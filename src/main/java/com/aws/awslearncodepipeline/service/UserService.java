package com.aws.awslearncodepipeline.service;

import com.aws.awslearncodepipeline.model.Users;
import com.aws.awslearncodepipeline.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    /**
     * Registers a new user and encodes their password.
     *
     * @param user the user to be registered
     * @return the registered user with encoded password
     * @throws IOException if an error occurs during registration
     */
    public Users register(Users user) throws IOException {
        LOGGER.info("Registering new user: {}", user.getUsername());
        user.setPassword(encoder.encode(user.getPassword()));
        repo.addUser(user);
        LOGGER.info("User registered successfully: {}", user.getUsername());
        return user;
    }

    /**
     * Verifies a user's credentials and generates a JWT token upon successful authentication.
     *
     * @param user the user attempting to log in
     * @return the JWT token if authentication is successful, "fail" otherwise
     */
    public String verify(Users user) {
        LOGGER.info("Starting authentication process for user: {}", user.getUsername());

        try {
            // Create the authentication token
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            // Authenticate the user
            Authentication authentication = authManager.authenticate(authToken);

            // Check if authentication is successful
            if (authentication.isAuthenticated()) {
                // Get the authenticated user's details (including roles)
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                // Log successful authentication
                LOGGER.info("Authentication successful for user: {}, with authorities: {}", userDetails.getUsername(), userDetails.getAuthorities());

                // Generate JWT token with user details and roles
                String jwtToken = jwtService.generateToken(userDetails);

                // Log token generation
                LOGGER.info("Generated JWT token for user: {}", userDetails.getUsername());

                return jwtToken;
            } else {
                // Log failed authentication
                LOGGER.warn("Authentication failed for user: {}", user.getUsername());
                return "fail";
            }
        } catch (Exception e) {
            // Log exception details
            LOGGER.error("Exception occurred during authentication for user: {}", user.getUsername(), e);
            return "fail";
        }
    }
}
