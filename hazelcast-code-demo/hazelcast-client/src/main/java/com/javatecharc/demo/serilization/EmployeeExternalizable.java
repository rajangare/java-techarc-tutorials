package com.javatecharc.demo.serilization;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.*;

public class EmployeeExternalizable implements IdentifiedDataSerializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private String phoneNo;

    public EmployeeExternalizable(String name, String email, String phoneNo) {
        super();
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }

    @Override
    public int getFactoryId() {
        return 0;
    }

    @Override
    public int getClassId() {
        return 0;
    }

    @Override
    public void writeData(ObjectDataOutput objectDataOutput) throws IOException {
        System.out.println("Serializing....");
        objectDataOutput.writeUTF(name);
    }

    @Override
    public void readData(ObjectDataInput objectDataInput) throws IOException {
        System.out.println("Deserializaing....");
        this.name = objectDataInput.readUTF();
    }
}
