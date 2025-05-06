package com.javatecharc.learning.controller;

import com.javatecharc.learning.service.LearningAOPService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/aop")
public class AOPDemoController {
    private final LearningAOPService learningAOPService;

    public AOPDemoController(LearningAOPService learningAOPService) {
        this.learningAOPService = learningAOPService;
    }

    @GetMapping("/demo")
    public String aopDemo(String name) {
        return learningAOPService.aopLearning(name);
    }
}
