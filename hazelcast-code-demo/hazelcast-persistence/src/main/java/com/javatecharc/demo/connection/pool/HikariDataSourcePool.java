package com.javatecharc.demo.connection.pool;

import com.hazelcast.shaded.com.zaxxer.hikari.HikariConfig;
import com.hazelcast.shaded.com.zaxxer.hikari.HikariDataSource;
import com.javatecharc.demo.connection.ConnectionPool;
import com.javatecharc.demo.exception.DatabaseSQLException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class HikariDataSourcePool implements ConnectionPool {
    private static HikariDataSource hikariDataSource = null;

    private static HikariConfig hikariConfig;

    public HikariDataSourcePool() {
        if (null != hikariDataSource) {
            System.out.println("*** Hey! Mr. XYZ HikariDataSource already created.. existing connection can be used.");
        } else {
            createHikariDataSource();
        }
    }

    private void createHikariDataSource() {
        hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:h2:mem:testdb");
        hikariConfig.setUsername("sa");
        hikariConfig.setPassword("");
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setPoolName("Demo-POOL");
        hikariConfig.setDriverClassName("org.h2.Driver");

        hikariDataSource = new HikariDataSource(hikariConfig);

        log.info("Datasource Created..");
    }

    @Override
    public Connection getConnection() {
        try {
            if (null != hikariDataSource) {
                System.err.println("\nGetting....! SQL Connection from HIKARI POOL.\n");
                return hikariDataSource.getConnection();
            } else {
                throw new DatabaseSQLException("Ops! Hikari datasource not available.");
            }
        } catch (SQLException e) {
            throw new DatabaseSQLException("Exception while creating database connection."+e);
        }
    }
}
