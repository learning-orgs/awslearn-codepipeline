package com.aws.awslearncodepipeline.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int id;
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();
}