package com.javatecharc.demo.connection;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();
}
