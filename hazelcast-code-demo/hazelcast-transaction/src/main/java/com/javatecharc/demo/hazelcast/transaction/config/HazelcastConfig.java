package com.javatecharc.demo.hazelcast.transaction.config;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastConfig {
    public static HazelcastInstance createHazelcastInstance() {
        Config config = new Config();
        return Hazelcast.newHazelcastInstance(config);
    }
}
