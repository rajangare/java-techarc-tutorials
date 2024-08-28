package com.javatecharc.demo.maps;

import com.javatecharc.demo.connection.ConnectionPool;
import com.javatecharc.demo.connection.pool.HikariDataSourcePool;
import com.javatecharc.demo.exception.DatabaseSQLException;
import com.javatecharc.demo.model.Employee;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class EmployeeMapStoreFactory extends AbstractDataStoreFactory<Integer, Employee> {
    private ConnectionPool pool;

    public EmployeeMapStoreFactory() {
        pool = new HikariDataSourcePool();
    }

    @Override
    public Iterable<Integer> loadAllKeys() {
        String query = "SELECT EMPID FROM EMPLOYEE";

        List<Integer> empIds = new ArrayList<>();
        try(Connection connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                empIds.add(resultSet.getInt(1));
            }
        } catch (SQLException exception) {
            throw new DatabaseSQLException("Error on load all keys : "+exception);
        }

        return empIds;
    }

    @Override
    public Employee load(Integer empId) {
        String query = "SELECT EMPID, FIRSTNAME, LASTNAME, EMAIL, SALARY FROM EMPLOYEE WHERE EMPID=?";
        Employee.EmployeeBuilder employeeBuilder = Employee.builder();
        try(Connection connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {
            preparedStatement.setInt(1, empId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employeeBuilder
                        .empId(resultSet.getInt(1))
                        .firstName(resultSet.getString(2))
                        .lastName(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .salary(resultSet.getDouble(5));
            }
        } catch (SQLException exception) {
            throw new DatabaseSQLException("Error on load key : "+exception);
        }

        return employeeBuilder.build();
    }

    @Override
    public Map<Integer, String> loadAll(Collection collection) {
        log.info("Load all employee..");

        List<Integer> employees = (List<Integer>) collection;

        return employees.stream().collect(Collectors.toMap(id -> id, id -> load(id).toString()));
    }
}
