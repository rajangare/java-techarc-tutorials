package com.javatecharc.demo.config;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Bean
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance);
    }
}
