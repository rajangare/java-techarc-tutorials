package com.javatecharc.demo.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.collection.IQueue;
import com.hazelcast.config.SSLConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

@Configuration
public class HazelcastClientConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {

        ClientConfig config = new ClientConfig();
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        //HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        ClientNetworkConfig clientNetworkConfig = config.getNetworkConfig();

        Properties properties = new Properties();
        properties.put("hazelcast.instance", "dev");
        properties.put("hazelcast.clustername", "hz-test");
        properties.put("hazelcast.password", "");

        clientNetworkConfig.setSSLConfig(new SSLConfig()
                .setEnabled(false)
                .setProperties(properties));
        clientNetworkConfig.setAddresses(List.of("localhost:5701"));
        config.setClusterName("dev");
        config.setInstanceName("hz-test");

        return HazelcastClient.newHazelcastClient(config);
    }
}
