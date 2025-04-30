package com.javatecharc.learning.service;

import com.javatecharc.learning.entity.Employee;
import com.javatecharc.learning.exception.EmployeeNotFound;
import com.javatecharc.learning.model.EmployeeRequest;
import com.javatecharc.learning.model.EmployeeResponse;
import com.javatecharc.learning.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    public final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String registerEmployee(EmployeeRequest request) {
        Employee employee = new Employee();

        System.out.println(request);

        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());

        System.out.println(employee);

        employee = employeeRepository.save(employee);
        System.out.println(employee);
        return "Employee Created Successfully!";
    }

    public EmployeeResponse getEmployeeById(Long id) {
        EmployeeResponse response = new EmployeeResponse();

        try {
            Employee employee = employeeRepository.getById(id);
            System.out.println(employee);

            response.setId(employee.getId());
            response.setName(employee.getName());
            response.setDepartment(employee.getDepartment());
            response.setSalary(employee.getSalary());

        } catch (Exception e) {
            throw new EmployeeNotFound("Employee not found with id: " + id);
        }

        return response;
    }

    public EmployeeResponse getEmployeeByName(String name) {
        EmployeeResponse response = new EmployeeResponse();

        try {
            Employee employee = employeeRepository.findByName(name);
            System.out.println(employee);

            response.setId(employee.getId());
            response.setName(employee.getName());
            response.setDepartment(employee.getDepartment());
            response.setSalary(employee.getSalary());

        } catch (Exception e) {
            throw new EmployeeNotFound("Employee not found with name: " + name);
        }

        return response;
    }

    public EmployeeResponse getEmployeeSalaryGreaterThan(Double salary) {
        EmployeeResponse response = new EmployeeResponse();

        try {
            Employee employee = employeeRepository.findSalaryGreaterThan(salary);
            System.out.println(employee);

            response.setId(employee.getId());
            response.setName(employee.getName());
            response.setDepartment(employee.getDepartment());
            response.setSalary(employee.getSalary());

        } catch (Exception e) {
            throw new EmployeeNotFound("Employee not found with salary greater : " + salary);
        }

        return response;
    }


}
