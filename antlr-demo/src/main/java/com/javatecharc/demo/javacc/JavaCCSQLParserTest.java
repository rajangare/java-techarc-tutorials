package com.javatecharc.demo.javacc;

import java.io.StringReader;

public class JavaCCSQLParserTest {
    public static void main(String[] args) {
        String sql = "SELECT id, name FROM users WHERE age = 30 AND city = 10";

        try {
            JavaCCSQLParser parser = new JavaCCSQLParser(new StringReader(sql));
            parser.Statement();  // This will parse the SQL statement.
            System.out.println("SQL parsed successfully!");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
