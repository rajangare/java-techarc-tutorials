package com.javatecharc.demo;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;

public class DemoEntryListener implements EntryAddedListener<Integer, String> {
    @Override
    public void entryAdded(EntryEvent entryEvent) {
        System.out.println("Do something for entry added event... ");
    }
}
