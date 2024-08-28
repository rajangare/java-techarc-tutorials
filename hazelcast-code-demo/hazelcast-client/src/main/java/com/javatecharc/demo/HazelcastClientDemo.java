package com.javatecharc.demo;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.SSLConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.List;
import java.util.Properties;

public class HazelcastClientDemo {
    public static void main(String[] args) {
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

        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(config);

        IMap<String, String> testMap = hazelcastInstance.getMap("test-map");
        System.out.println("Size before add : "+testMap.size());

        testMap.put("1", "JavaTechARC3i");
        System.out.println("Size add add : "+testMap.size());
        System.out.println("Map Data : "+testMap);
    }
}
