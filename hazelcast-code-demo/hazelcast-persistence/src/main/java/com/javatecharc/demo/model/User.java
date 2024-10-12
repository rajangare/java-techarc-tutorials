package com.javatecharc.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class User implements Serializable, Comparable<User> {
    private int id;
    private String name;
    private String email;
    private int age;

    @Override
    public int compareTo(User user) {
        return this.id - user.id;
    }

    //Create setter, getter or use lombok @Setter, @Getter
}
