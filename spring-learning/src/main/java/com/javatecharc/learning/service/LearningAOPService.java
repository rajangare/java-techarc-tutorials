package com.javatecharc.learning.service;

import org.springframework.stereotype.Service;

@Service
public class LearningAOPService {
    public String aopLearning(String name) {
        return "Hello! AOP Demo, " + name + "!";
    }
}
