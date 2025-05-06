package com.javatecharc.learning.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.javatecharc.demo.service.LearningAOPService.aopLearning(..))")
    public void logBeforeMethodExecution() {
        System.out.println("Executing LearningAOPService.aopLearning() method...");
    }
}
