package com.javatecharc.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Players {
    private int userId;
    private String name;
    private String department;
    private int salary;
}