package com.javatecharc.demo.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SSLConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

@Configuration
public class HazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {

        ClientConfig config = new ClientConfig();
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        //HazelcastInstance hz = HazelcastClient.newHazelcastClient();

        ClientNetworkConfig clientNetworkConfig = config.getNetworkConfig();

        Properties properties = new Properties();
        properties.put("hazelcast.instance", "dev");
        properties.put("hazelcast.clustername", "javaTechARC");
        properties.put("hazelcast.password", "");

        clientNetworkConfig.setSSLConfig(new SSLConfig()
                .setEnabled(false)
                .setProperties(properties));
        clientNetworkConfig.setAddresses(List.of("localhost:5701"));
        config.setClusterName("dev");
        config.setInstanceName("javaTechARC");

        return HazelcastClient.newHazelcastClient(config);
    }

    @Bean
    public Config mapConfig() {
        Config config = new Config();

        // Configure a map with a specific name
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("javaTechARC_Map");
        mapConfig.setTimeToLiveSeconds(300); // TTL Config

        config.addMapConfig(mapConfig);

        return config;
    }
}
