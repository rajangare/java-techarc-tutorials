package com.javatecharc.demo.compact.serializer;

import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import com.javatecharc.demo.model.Student;

public class StudentCompactSerializer implements CompactSerializer<Student> {
    @Override
    public Student read(CompactReader compactReader) {
        int id = (int) compactReader.readInt64("id");
        String name = compactReader.readString("name");
        String department = compactReader.readString("department");
        return new Student(id, name, department);
    }

    @Override
    public void write(CompactWriter writer, Student student) {
        writer.writeInt64("id", student.getId());
        writer.writeString("name", student.getName());
        writer.writeString("department", student.getDepartment());
    }

    @Override
    public String getTypeName() {
        return "Student";
    }

    @Override
    public Class<Student> getCompactClass() {
        return Student.class;
    }
}
