package com.javatecharc.demo;

import antlr.v1.SQLLexer;
import antlr.v1.SQLParser;
import com.javatecharc.demo.connection.ConnectionPool;
import com.javatecharc.demo.connection.pool.HikariDataSourcePool;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.sql.*;

public class AntlrSQLParserExample {
    public static void main(String[] args) {
        String sql = "SELECT EmpId, FirstName, LastName, Email, PhoneNo, Salary FROM EMPLOYEE where empId = 101";

        // Create a CharStream from the input string
        CharStream input = CharStreams.fromString(sql);

        // Create a lexer that feeds off of input CharStream
        SQLLexer lexer = new SQLLexer(input);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        SQLParser parser = new SQLParser(tokens);

        // Begin parsing at the 'sql_stmt' rule
        ParseTree tree = parser.sql_stmt();

        // Print LISP-style tree
        System.out.println(tree.toStringTree(parser));

        executeSQLQuery(sql);
    }

    private static void executeSQLQuery(String sql) {
        // JDBC connection details
        String jdbcUrl = "jdbc:h2:mem:testdb";
        String username = "sa";
        String password = "";

        ConnectionPool pool = new HikariDataSourcePool();

        try (Connection conn = pool.getConnection();
             Statement stmt = conn.createStatement()) {

            // Execute the query
            ResultSet rs = stmt.executeQuery(sql);

            // Process the result set
            while (rs.next()) {
                System.out.println("Id: " + rs.getString("empId") + ", FirstName: " + rs.getInt("FirstName"));
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
