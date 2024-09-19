package com.aws.awslearncodepipeline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String hello() {
        return "hello";
    }
    @GetMapping("/v1")
    public String hello1() {
        return "hello1";
    }
    @GetMapping("/v2")
    public String hello2() {
        return "hello2";
    }
    @GetMapping("/v3")
    public String hello3() {
        return "hello3";
    }
}
