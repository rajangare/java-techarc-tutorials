package com.javatecharc.learning.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPLOYEE") // Maps the class to the EMPLOYEE table in the database.
public class Employee {
    @Id // Specifies the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates the primary key value.
    private Long id;

    @Column(name = "EMP_NAME") // Maps the field to the NAME column in the database.
    private String name;

    private String department;

    private Double salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

