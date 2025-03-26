package com.javatecharc.demo.hazelcast.transaction;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.Transaction;
import javax.transaction.xa.XAResource;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.transaction.HazelcastXAResource;
import com.hazelcast.transaction.TransactionContext;
import com.javatecharc.demo.hazelcast.transaction.model.Customer;

public class HazelcastXATransactionDemo {

    public static String MAP_NAME = "customer_map";
    public static String SECOND_MAP = "secondMap";
    public static String UNIQUE_ID_MAP = "unique_map_identity";

    public static void main(String[] args) throws Exception {
        boolean usePutIfAbsent = false;
        HazelcastXATransactionDemo demo = new HazelcastXATransactionDemo();
        HazelcastInstance hazelcastInstance = InitialProcess.getHzClient();
        XADataSource xaDataSource = InitialProcess.getXADataSource("localhost");

        InitialProcess.cleanup(xaDataSource, hazelcastInstance);
        InitialProcess.createTable(xaDataSource);


        demo.testDemo(hazelcastInstance, xaDataSource, new Customer(1, "John", 1), "JavaTech ARC First test", true, usePutIfAbsent);
        demo.testDemo(hazelcastInstance, xaDataSource, new Customer(2, "Michael", 2), "JavaTech ARC Second test", false, usePutIfAbsent);
        demo.testDemo(hazelcastInstance, xaDataSource, new Customer(3, "Andrew", 1), "JavaTech ARC Third test", true, usePutIfAbsent);

        hazelcastInstance.shutdown();
    }

    private void testDemo(HazelcastInstance hzClient, XADataSource pgXADataSource, Customer customer, String testName, boolean shouldInsert, boolean usePutIfAbsent)
            throws Exception {
        if(usePutIfAbsent) {
            IMap<Long, Long> uniqueIdMap = hzClient.getMap(UNIQUE_ID_MAP);
            Long id = uniqueIdMap.putIfAbsent(customer.getUniqueId(), customer.getId());
            if(id != null && id == customer.getId()) {
                //this is an update and should be allowed
            }else if(id != null && id != customer.getId()) {
                //this is an insert and should be stopped
                return;
            }else{//id is null
                //this is an insert and should be allowed
            }

        }
        testTransactions(hzClient, pgXADataSource, customer);
        boolean isInserted = isInserted(hzClient, customer);
        if (isInserted == shouldInsert) {
            System.out.println(testName + " was successful");
        } else {
            System.out.println(testName + " failed");
        }
    }

    private void testTransactions(HazelcastInstance hazelcastInstance, XADataSource pgXADataSource, Customer customer)
            throws Exception {
        // I am using atomikos but you can use any other JTA provider
        cleanAtomikosLogs();
        UserTransactionManager tm = new UserTransactionManager();
        tm.begin();

        Transaction transaction = tm.getTransaction();

        HazelcastXAResource xaResource = hazelcastInstance.getXAResource();
        XAConnection pgXAConnection = pgXADataSource.getXAConnection();
        XAResource pgXAResource = pgXAConnection.getXAResource();
        transaction.enlistResource(pgXAResource);
        transaction.enlistResource(xaResource);
        boolean rollback = false;
        try (Connection connection = pgXAConnection.getConnection()) {
            connection.setAutoCommit(false);
            TransactionContext context = xaResource.getTransactionContext();
            context.getMap(MAP_NAME).put(customer.getId(), customer);//insert to first map
            context.getMap(SECOND_MAP).put(customer.getId(), customer);//insert to second map
            InitialProcess.executeStatement(connection, false,
                    "INSERT INTO customer_one (id, name, uniqueId) VALUES (?, ?, ?)", customer.getId(), customer.getName(),
                    customer.getUniqueId());
            // if(true)
            //     throw new RuntimeException("Simulating a failure");
            transaction.delistResource(xaResource, XAResource.TMSUCCESS);
            transaction.delistResource(pgXAResource, XAResource.TMSUCCESS);
            tm.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            rollback = true;
        } finally {
            if (rollback) {
                try {
                    tm.rollback();
                } catch (Exception rollbackException) {
                    System.out.println("Error during rollback: " + rollbackException.getMessage());
                }
            }
        }
        cleanAtomikosLogs();
    }
    private boolean isInserted(HazelcastInstance hzClient, Customer customer) {
        boolean present = false;
        // also check if the data is present in the database
        try {
            Connection connection = InitialProcess.getXADataSource("localhost").getXAConnection().getConnection();
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery("SELECT * FROM customer_one WHERE id = " + customer.getId());
            if (resultSet.next()) {
                present = resultSet.getInt("id") == customer.getId() && resultSet.getString("name").equals(customer.getName())
                        && resultSet.getInt("uniqueId") == customer.getUniqueId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        IMap<Long, Customer> map = hzClient.getMap(MAP_NAME);
        // IMap<Long, Customer> anotherMap = hzClient.getMap(ANOTHER_MAP_NAME);
        return present
                && map.get(customer.getId()) != null && map.get(customer.getId()).getUniqueId() == customer.getUniqueId();

    }

    private static void cleanAtomikosLogs() {
        try {
            File currentDir = new File(".");
            final File[] tmLogs = currentDir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".epoch") || name.startsWith("tmlog");
                }
            });
            for (File tmLog : tmLogs) {
                tmLog.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}