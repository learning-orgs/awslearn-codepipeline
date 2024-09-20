package com.aws.awslearncodepipeline.service;

import com.aws.awslearncodepipeline.model.UserPrincipal;
import com.aws.awslearncodepipeline.model.Users;
import com.aws.awslearncodepipeline.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Attempting to load user by username: {}", username); // Log the username being searched

        Users user = null;
        try {
            user = userRepo.findUserByUsername(username);
            if (user == null) {
                LOGGER.warn("User with username '{}' not found in the database.", username); // Warn if user not found
                throw new UsernameNotFoundException("User not found");
            }
            LOGGER.info("User '{}' found in the database.", username); // Log success when user is found
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching user details for username '{}': {}", username, e.getMessage()); // Log any exceptions
            throw new RuntimeException("Some issue occurred while fetching user details", e);
        }

        LOGGER.debug("Returning UserPrincipal object for user: {}", username); // Debug log for returning UserPrincipal
        return new UserPrincipal(user);
    }
}
