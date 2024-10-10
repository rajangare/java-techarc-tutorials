package com.javatecharc.demo.query;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.sql.SqlResult;
import com.hazelcast.sql.SqlRow;
import com.hazelcast.sql.SqlService;
import com.javatecharc.demo.model.Players;

public class HazelcastQueryExample {
    public static void main(String[] args) {
        Config config = new Config(); config. getJetConfig(). setEnabled(true);
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        IMap<Integer, Players> players = hazelcastInstance.getMap("players");

        //Put Player data
        players.put(1, new Players(1, "Sachin", "Opening Batsman", 75000));
        players.put(2, new Players(2, "Rahul", "Middle Order", 90000));
        players.put(3, new Players(3, "Rohit", "Opening Batsman", 80000));
        players.put(4, new Players(4, "Dhoni", "Finisher", 85000));
        players.put(5, new Players(5, "Yuvraj", "Middle Order", 70000));

        SqlService sqlService = hazelcastInstance.getSql();

        //Then we define the schema in Hazelcast:
        String mapping = "CREATE MAPPING players " +
                "TYPE IMap " +
                "OPTIONS (" +
                "'keyFormat' = 'java', " +
                "'keyJavaClass' = 'java.lang.Integer', " +
                "'valueFormat' = 'java', " +
                "'valueJavaClass' = 'com.javatecharc.demo.model.Players'" +
                ")";

        sqlService.execute(mapping);

        //Executing SQL Queries
        SqlResult result = sqlService.execute("SELECT * FROM players WHERE salary > 80000");
        for (SqlRow row : result) {
            System.out.println(row);
        }

        //Average Salary
        SqlResult averageSalary = sqlService.execute("SELECT AVG(salary) FROM players");
        for (SqlRow row : averageSalary) {
            System.out.println("Average salary: " + row.getObject(0));
        }

        String joinQuery = "SELECT e.name, d.name FROM players p " +
                "JOIN departments d ON p.departmentId = d.id";

        SqlResult joinResult = sqlService.execute(joinQuery);
        for (SqlRow row : joinResult) {
            System.out.println(row.getObject(0) + " works in " + row.getObject(1));
        }

        sqlService.execute("INSERT INTO players VALUES (6, 'Ganguly', 'Captain', 950000)");

        sqlService.execute("UPDATE players SET salary = 85000 WHERE name = 'Dhoni'");
    }
}
