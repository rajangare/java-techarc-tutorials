package com.javatecharc.learning.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class Student {
    private String name;

    @PostConstruct
    public void init() {
        // Initialization logic, db connection, file system connection, etc.
        System.out.println("Student bean is initialized!");
    }

    public void demo() {
        System.out.println("Hello, How are you!");
    }

    @PreDestroy
    public void destroy() {
        // Cleanup logic, closing db connection, file system connection, etc.
        System.out.println("Student bean is destroyed!");
    }

    public String getName() {
        return name;
    }

    public void registerStudent() {
        System.out.println("Student is registered!");
    }

}
