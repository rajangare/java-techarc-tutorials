package com.javatecharc.demo.maps;

import com.hazelcast.internal.serialization.Data;
import com.hazelcast.map.MapLoader;
import com.hazelcast.map.MapStore;
import com.hazelcast.map.impl.mapstore.MapDataStore;
import com.hazelcast.map.impl.mapstore.writebehind.TxnReservedCapacityCounter;
import com.hazelcast.map.impl.mapstore.writebehind.entry.DelayedEntry;

import java.util.Collection;
import java.util.UUID;

public abstract class AbstractDataStoreFactory<K, V> implements MapDataStore<K, V>, MapLoader<K, V>, MapStore<K, V> {

    @Override
    public V add(K k, V v, long l, long l1, UUID uuid) {
        return null;
    }

    @Override
    public V addBackup(K k, V v, long l, long l1, UUID uuid) {
        return null;
    }

    @Override
    public void addForcibly(DelayedEntry<Data, Object> delayedEntry) {

    }

    @Override
    public void addTransient(K k, long l) {

    }

    @Override
    public void remove(K k, long l, UUID uuid) {

    }

    @Override
    public void removeBackup(K k, long l, UUID uuid) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void removeAll(Collection collection) {

    }

    @Override
    public boolean loadable(K k) {
        return false;
    }

    @Override
    public int notFinishedOperationsCount() {
        return 0;
    }

    @Override
    public boolean isPostProcessingMapStore() {
        return false;
    }

    @Override
    public long softFlush() {
        return 0;
    }

    @Override
    public void hardFlush() {

    }

    @Override
    public V flush(K k, V v, boolean b) {
        return null;
    }

    @Override
    public boolean isWithExpirationTime() {
        return false;
    }

    @Override
    public TxnReservedCapacityCounter getTxnReservedCapacityCounter() {
        return null;
    }
}
