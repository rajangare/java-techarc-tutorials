package com.javatecharc.demo.grpc.service;

import com.javatecharc.demo.protobuf.grpc.EmployeeRequest;
import com.javatecharc.demo.protobuf.grpc.EmployeeResponse;
import com.javatecharc.demo.protobuf.grpc.EmployeeServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.HashMap;
import java.util.Map;

@GrpcService
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @Override
    public void getEmployeeDetails(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
        Integer empId = request.getEmpId();
        System.out.println("Received request for employee : " + request);

        Map<Integer, EmployeeResponse> employeeMap = getEmployeeDataMap();

        EmployeeResponse response = employeeMap.get(empId);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private Map<Integer, EmployeeResponse> getEmployeeDataMap() {
        // This method simulates a database or Hazelcast Map for employee details.
        Map<Integer, EmployeeResponse> employeeMap = new HashMap<>();

        employeeMap.put(101, EmployeeResponse.newBuilder()
                .setEmpId(101)
                .setName("John Smith")
                .setDepartment("Engineering")
                .setSalary(30000)
                .build());

        employeeMap.put(102, EmployeeResponse.newBuilder()
                .setEmpId(102)
                .setName("Jane Brown")
                .setDepartment("HR")
                .setSalary(25000)
                .build());

        employeeMap.put(103, EmployeeResponse.newBuilder()
                .setEmpId(103)
                .setName("Alice Johnson")
                .setDepartment("Finance")
                .setSalary(50000)
                .build());

        return employeeMap;
    }
}
