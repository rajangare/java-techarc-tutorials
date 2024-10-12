package com.javatecharc.demo.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

public class DescendingUserIdComparator implements Serializable, Comparator<Map.Entry<Integer, User>> {

    @Override
    public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
        User s1 = o1.getValue();
        User s2 = o2.getValue();
        return s2.getId() - s1.getId();
    }
}

