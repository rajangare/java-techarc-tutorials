package com.javatecharc.demo.jet;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sources;
import com.hazelcast.jet.pipeline.JoinClause;
import com.hazelcast.jet.pipeline.Sinks;

public class JetJoinExample {
    /*public static void main(String[] args) {
        JetInstance jet = Jet.newJetInstance();

        // Define a Jet pipeline to join customerMap and orderMap on customerId
        Pipeline pipeline = Pipeline.create();
        pipeline.readFrom(Sources.map("customerMap"))
                .join(Sources.map("orderMap"),
                        JoinClause.joinMapEntries("id", "customerId"), // Join on customer ID
                        (customer, order) -> new JoinedResult(customer, order)) // Customize as needed
                .writeTo(Sinks.list("joinedResults")); // Write joined results to a list

        jet.newJob(pipeline).join();
    }*/
}