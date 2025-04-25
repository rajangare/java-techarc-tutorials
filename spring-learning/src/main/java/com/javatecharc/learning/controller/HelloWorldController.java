package com.javatecharc.learning.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {
    @GetMapping("/hello")
    @Operation(summary = "Hello World first program")
    public String sayHello() {
        return "Welcome to Spring Boot Student!";
    }

    @GetMapping("/name")
    @Operation(summary = "Hello World with Name")
    public String helloName(@RequestParam(name = "Name") String name) {
        return "Welcome "+name+" to Spring Boot session!";
    }
}
