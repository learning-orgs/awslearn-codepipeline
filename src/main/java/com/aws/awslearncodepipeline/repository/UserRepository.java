package com.aws.awslearncodepipeline.repository;


import com.aws.awslearncodepipeline.model.Users;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface UserRepository {

    // Get all users from Excel
    List<Users> getUsersFromExcel() throws IOException;

    // Add a new user to Excel
    void addUser(Users user) throws IOException;

    // Update user details in Excel
    void updateUser(Users user) throws IOException;

    // Delete a user by username from Excel
    void deleteUser(String username) throws IOException;

    // Find user by username in Excel
    Users findUserByUsername(String username) throws IOException;
}