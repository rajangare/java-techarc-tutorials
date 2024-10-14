package com.javatecharc.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private int id;
    private String name;
    private String department;

    public Student(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    //Generate Getter and Setter
}
