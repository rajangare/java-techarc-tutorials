package com.javatecharc.demo.predicate;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import com.javatecharc.demo.model.DescendingUserIdComparator;
import com.javatecharc.demo.model.User;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

public class HazelcastPagingExample {
    public static void main(String[] args) {
        // Step 1: Initialize Hazelcast instance
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Step 2: Create a distributed map
        IMap<Integer, User> userMap = hazelcastInstance.getMap("users");

        //Step 3: Create sample data
        for (int i = 1; i <= 50; i++) {
            userMap.put(i, new User(100 + i, "User" + i, "user" + i + "@abc.com", 20 + i));
        }

        //Print users
        userMap.values().forEach(System.out::println);

        Predicate<Integer, User> equalPredicate = Predicates.greaterEqual("age", 30);

        // Default Ascending order, but it can be arranged as a comparator which helps to sort in descending order of id field
        Comparator<Map.Entry<Integer, User>> descendingComparator = new DescendingUserIdComparator();

        //Create Paging predicate with page size 10
        PagingPredicate<Integer, User> pagingPredicate = Predicates.pagingPredicate(equalPredicate, descendingComparator, 10);

        //Apply Paging predicate
        Collection<User> results = userMap.values(pagingPredicate);

        //print users
        System.out.println("\n================Filtered Users================");
        System.out.println("\nPage 1 -> ");
        results.forEach(System.out::println);


        pagingPredicate.nextPage();
        results = userMap.values(pagingPredicate);
        //print users
        System.out.println("\n================Filtered Users================");
        System.out.println("\nPage 2 -> ");
        results.forEach(System.out::println);

        pagingPredicate.nextPage();
        results = userMap.values(pagingPredicate);
        //print users
        System.out.println("\n================Filtered Users================");
        System.out.println("\nPage 3 -> ");
        results.forEach(System.out::println);

        // a predicate which fetches 3 user for each page, natural order (see User.compareTo()),
        // does not filter out anything
        pagingPredicate = Predicates.pagingPredicate(3);

        // since first page is 0, we are requesting the 4th page here
        // expected result:
        pagingPredicate.setPage(3);
        results = userMap.values(pagingPredicate);
        System.out.println("\n================Filtered Users================");
        System.out.println("\nPage 4 -> ");
        results.forEach(System.out::println);

        System.out.println();

        //Shutdown hazelcast instance
        hazelcastInstance.shutdown();
    }
}
