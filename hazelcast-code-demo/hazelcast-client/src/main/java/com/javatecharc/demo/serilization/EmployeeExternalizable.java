package com.javatecharc.demo.serilization;

import java.io.*;

public class EmployeeExternalizable implements Externalizable {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Serializing....");
        out.writeUTF(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Deserializaing....");
        this.name = in.readUTF();
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
}
