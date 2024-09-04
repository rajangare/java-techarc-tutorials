package com.javatecharc.demo.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastMapConfig {
    @Bean
    public Config config() {
        Config config = new Config();

        // Configure a map with a specific name
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("javaTechARC_Map");
        mapConfig.setTimeToLiveSeconds(300); // TTL Config

        config.addMapConfig(mapConfig);

        return config;
    }
}
