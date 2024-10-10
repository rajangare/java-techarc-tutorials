package com.javatecharc.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@AllArgsConstructor
public class Employee {
    private Integer empId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;

    private Double salary;
}
