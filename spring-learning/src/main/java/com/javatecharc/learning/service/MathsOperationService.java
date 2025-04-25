package com.javatecharc.learning.service;

import org.springframework.stereotype.Service;

@Service
public class MathsOperationService {
    public int mathOperation(int a, int b, String operation) {
        return switch (operation) {
            case "add" -> a + b;
            case "sub" -> a - b;
            case "mul" -> a * b;
            case "div" -> a / b;
            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
        };
    }

    public void abc() {
        System.out.println("Hello ABC");
    }
}
