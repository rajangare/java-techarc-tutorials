package com.javatecharc.demo.queue;

import com.hazelcast.collection.QueueStore;
import com.javatecharc.demo.model.Employee;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class DemoQueueStore implements QueueStore<Employee> {
    @Override
    public void store(Long empId, Employee employee) {

    }

    @Override
    public void storeAll(Map<Long, Employee> map) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteAll(Collection<Long> collection) {

    }

    @Override
    public Employee load(Long aLong) {
        return null;
    }

    @Override
    public Map<Long, Employee> loadAll(Collection<Long> collection) {
        return Map.of();
    }

    @Override
    public Set<Long> loadAllKeys() {
        return Set.of();
    }
}
