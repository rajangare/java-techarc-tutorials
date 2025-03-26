package com.javatecharc.demo.hazelcast.transaction;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.transaction.TransactionContext;
import com.hazelcast.transaction.TransactionalMap;
import com.hazelcast.xa.HazelcastXAResource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class HazelcastXADemoExample {
    public static void main(String[] args) throws Exception {
        // Initialize Hazelcast instance
        Config config = new Config();
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        // Obtain XA Resource from Hazelcast
        HazelcastXAResource xaResource = hazelcastInstance.getXAResource();

        // Configure Database (H2 + HikariCP)
        DataSource dataSource = getDataSource();
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        // Create a unique transaction ID (Xid)
        Xid xid = new SampleXid(123, new byte[]{0x01}, new byte[]{0x02});

        // Start XA Transaction
        xaResource.start(xid, XAResource.TMNOFLAGS);

        // Begin Hazelcast Transaction
        TransactionContext context = hazelcastInstance.newTransactionContext();
        context.beginTransaction();

        // Hazelcast Operation
        TransactionalMap<Integer, String> txMap = context.getMap("JavaTechARCXAMap");
        txMap.put(1, "Hazelcast XA Transaction");

        // Database Operation
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)");
        stmt.setInt(1, 1);
        stmt.setString(2, "XA User");
        stmt.executeUpdate();

        // Prepare phase for two-phase commit
        xaResource.end(xid, XAResource.TMSUCCESS);
        int prepare = xaResource.prepare(xid);

        if (prepare == XAResource.XA_OK) {
            // Commit Hazelcast Transaction
            xaResource.commit(xid, false);
            context.commitTransaction();

            // Commit Database Transaction
            connection.commit();
            System.out.println("XA Transaction committed successfully!");
        } else {
            // Rollback if any issue
            xaResource.rollback(xid);
            context.rollbackTransaction();
            connection.rollback();
            System.out.println("XA Transaction rolled back!");
        }

        // Cleanup
        connection.close();
        hazelcastInstance.shutdown();
    }

    // Database Configuration using HikariCP
    private static DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        config.setUsername("sa");
        config.setPassword("");
        return new HikariDataSource(config);
    }
}
