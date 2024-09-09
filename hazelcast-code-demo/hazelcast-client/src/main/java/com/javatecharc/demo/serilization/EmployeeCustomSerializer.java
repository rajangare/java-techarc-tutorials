package com.javatecharc.demo.serilization;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;

public class EmployeeCustomSerializer implements StreamSerializer<Employee> {
    @Override
    public void write(ObjectDataOutput out, Employee employee) throws IOException {
        out.writeInt(employee.getEmpId());
        out.writeUTF(employee.getName());
    }

    @Override
    public Employee read(ObjectDataInput in) throws IOException {
        int id = in.readInt();
        String name = in.readUTF();
        return new Employee(id, name, null, null);
    }

    @Override
    public int getTypeId() {
        return 3;
    }

    @Override
    public void destroy() {
        // Optional cleanup
    }
}