package com.javatecharc.demo.serilization;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int empId;
    private String name;
    private String email;
    private String phoneNo;

    public Employee(int empId, String name, String email, String phoneNo) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
