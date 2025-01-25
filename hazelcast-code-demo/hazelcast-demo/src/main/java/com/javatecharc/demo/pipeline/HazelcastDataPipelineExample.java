package com.javatecharc.demo.pipeline;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.aggregate.AggregateOperations;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.SourceBuilder;
import com.hazelcast.jet.pipeline.Sources;

import java.util.Arrays;
import java.util.List;

public class HazelcastDataPipelineExample {
    /*public static void main(String[] args) {
        // Step 1: Start Hazelcast Jet Instance
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        JetInstance jetInstance = Jet.newJetInstance();

        try {
            // Step 2: Create Data Source
            List<String> inputData = getLogData();

            // Step 3: Define the Pipeline
            Pipeline pipeline = createPipeline

            // Add pipeline stages
            pipeline.readFrom(SourceBuilder
                            .batch("data-source", ctx -> inputData.iterator())
                            .build())
                    //.filter(line -> line.contains("ERROR")) // Filter for ERROR logs
                    //.map(line -> line.split(" - ")[1]) // Extract the error message
                    .groupingKey(errorMessage -> errorMessage) // Group by error message
                    .aggregate(AggregateOperations.counting()) // Count occurrences
                    .writeTo(Sinks.map("errorCounts")); // Store in Hazelcast Map

            // Step 4: Execute the Pipeline
            jetInstance.newJob(pipeline).join();

            // Step 5: Fetch Results from Hazelcast Map
            System.out.println("Processed Error Counts:");
            jetInstance.getMap("errorCounts").forEach((key, value) ->
                    System.out.println(key + " -> " + value));

        } finally {
            // Shutdown the Jet Instance
            //Jet.shutdownAll();
        }
    }

    private static List<String> getLogData() {
        return Arrays.asList(
                "INFO - Service started",
                "ERROR - Database connection failed",
                "INFO - User login successful",
                "ERROR - Timeout while calling API",
                "ERROR - NullPointerException at line 45"
        );
    }

    private Pipeline createPipeline() {
        Pipeline pipeline = Pipeline.create();
        pipeline.readFrom(Sources.list())


        return pipeline;
    }*/
}