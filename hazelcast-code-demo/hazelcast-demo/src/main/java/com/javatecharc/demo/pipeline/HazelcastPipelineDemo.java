package com.javatecharc.demo.pipeline;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.jet.JetService;
import com.hazelcast.jet.aggregate.AggregateOperations;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.Sources;

import java.util.Arrays;
import java.util.List;

public class HazelcastPipelineDemo {
    private static final String LIST_NAME = "errorList";
    private static final String MAP_NAME = "errorMap";

    public static void main(String[] args) {
        HazelcastPipelineDemo hazelcastPipelineDemo = new HazelcastPipelineDemo();

        hazelcastPipelineDemo.processDataPipeline();
    }

    private void processDataPipeline() {
        Config config = new Config();
        config.getJetConfig().setEnabled(true); // Enable the Jet engine

        // Step 1: Start Hazelcast Jet Instance
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        JetService jetService = hazelcastInstance.getJet();

        // Step 2: Create Data Source
        List<String> inputData = hazelcastInstance.getList(LIST_NAME);
        inputData.addAll(getLogData());


        // Step 3: Define the Pipeline
        Pipeline pipeline = createPipeline(inputData);

        // Step 4: Execute the Pipeline
        jetService.newJob(pipeline).join();

        // Step 5: Fetch Results from Hazelcast Map
        System.out.println("Processed Error Counts:");
        hazelcastInstance.getMap(MAP_NAME).forEach((key, value) ->
                System.out.println(key + " -> " + value));

        //Step 6: Shutdown the hazelcast Instance
        Hazelcast.shutdownAll();
    }

    private List<String> getLogData() {
        return Arrays.asList(
                "INFO - Service started",
                "ERROR - Database connection failed",
                "INFO - User login successful",
                "ERROR - Timeout while calling API",
                "ERROR - NullPointerException at line 45"
        );
    }

    private Pipeline createPipeline(List<String> inputData) {
        System.out.println(inputData);
        Pipeline pipeline = Pipeline.create();
        pipeline.readFrom(Sources.list(LIST_NAME))
                .filter(line -> line.toString().contains("ERROR")) // Filter for ERROR logs
                .map(line -> line.toString().split(" - ")[1]) // Extract the error message
                .groupingKey(errorMessage -> errorMessage) // Group by error message
                .aggregate(AggregateOperations.counting()) // Count occurrences
                .writeTo(Sinks.map(MAP_NAME)); // Store in Hazelcast Map

        return pipeline;
    }
}
