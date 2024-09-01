package com.javatecharc.demo.queue;

import com.google.gson.Gson;
import com.hazelcast.collection.QueueStore;
import com.javatecharc.demo.connection.ConnectionPool;
import com.javatecharc.demo.connection.pool.HikariDataSourcePool;
import com.javatecharc.demo.exception.DatabaseSQLException;
import com.javatecharc.demo.model.Employee;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Slf4j
public class DemoQueueStore implements QueueStore<Employee> {

    ConnectionPool pool = new HikariDataSourcePool();

    /**
     * Employee Queue has 2 column empId as key of Q and Data into Json format
     * @param empId
     * @param employee
     */
    @Override
    public void store(Long empId, Employee employee) {
        String storeQuery = "INSERT INTO EMPLOYEE_UPDATED_QUEUE(EMPID, DATA) VALUES(?, to_json(?::jsonb)";
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(storeQuery)) {
            preparedStatement.setInt(1, employee.getEmpId());
            preparedStatement.setString(2, new Gson().toJson(employee));

            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            log.error("Exception : {}", exception);
            throw new DatabaseSQLException(exception.getMessage());
        }

    }

    @Override
    public void storeAll(Map<Long, Employee> map) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteAll(Collection<Long> collection) {

    }

    @Override
    public Employee load(Long aLong) {
        return null;
    }

    @Override
    public Map<Long, Employee> loadAll(Collection<Long> collection) {
        return Map.of();
    }

    @Override
    public Set<Long> loadAllKeys() {
        return Set.of();
    }
}
