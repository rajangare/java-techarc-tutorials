package com.javatecharc.demo.serilization;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;

// Hazelcast Portable is deprecated since 5.4
public class EmployeePortable implements Portable {
    private int id;
    private String name;

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeInt("id", id);
        writer.writeUTF("name", name);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        id = reader.readInt("id");
        name = reader.readUTF("name");
    }

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return 2;
    }
}