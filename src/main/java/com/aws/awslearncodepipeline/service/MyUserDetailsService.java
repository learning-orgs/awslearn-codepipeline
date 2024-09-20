package com.aws.awslearncodepipeline.service;

import com.aws.awslearncodepipeline.model.UserPrincipal;
import com.aws.awslearncodepipeline.model.Users;
import com.aws.awslearncodepipeline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = null;
        try {
            user = userRepo.findUserByUsername(username);
            if (user == null) {
                System.out.println("User Not Found");
                throw new UsernameNotFoundException("user not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Some issue while fetching user details", e);
        }


        return new UserPrincipal(user);
    }
}