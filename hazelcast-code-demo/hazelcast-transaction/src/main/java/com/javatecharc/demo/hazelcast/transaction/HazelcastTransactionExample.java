package com.javatecharc.demo.hazelcast.transaction;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.transaction.TransactionContext;
import com.hazelcast.transaction.TransactionOptions;
import com.hazelcast.transaction.TransactionalMap;
import com.hazelcast.transaction.TransactionalQueue;

public class HazelcastTransactionExample {
    public static void main(String[] args) {
        // Step 1: Create Hazelcast instance
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // Step 2: Configure transaction options (TWO_PHASE or ONE_PHASE)
        TransactionOptions options = new TransactionOptions()
                .setTransactionType(TransactionOptions.TransactionType.TWO_PHASE);

        // Step 3: Start a transaction
        TransactionContext transactionContext = hazelcastInstance.newTransactionContext(options);
        transactionContext.beginTransaction();

        try {
            // Step 4: Perform operations inside the transaction
            TransactionalMap<String, String> transactionalMap = transactionContext.getMap("JavaTechARC_Map");
            TransactionalQueue<String> transactionalQueue = transactionContext.getQueue("JavaTechARC_Queue");

            transactionalMap.put("1", "Java Tech ARC 3i Hazelcast Transaction Map Test");
            transactionalQueue.offer("ava Tech ARC 3i Transaction Queue Test");

            // Simulate a condition to rollback, with condition true
            if (false) {  // Change to false to see successful commit
                throw new RuntimeException("Simulated Error: Rolling back transaction!");
            }

            // Step 5: Commit transaction if everything is fine
            transactionContext.commitTransaction();
            System.out.println("Transaction committed successfully!");

        } catch (Exception e) {
            // Step 6: Rollback in case of failure
            transactionContext.rollbackTransaction();
            System.out.println("Transaction rolled back due to error: " + e.getMessage());
        } finally {
            // Shutdown Hazelcast instance
            hazelcastInstance.shutdown();
        }
    }
}