package com.javatecharc.learning;

import com.javatecharc.learning.model.EmployeeRequest;
import com.javatecharc.learning.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearningApplication implements CommandLineRunner {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Main method to run the Spring Boot application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringLearningApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		EmployeeRequest request1 = new EmployeeRequest("Neeraj", "IT", 10000.0);
		employeeService.registerEmployee(request1);

		EmployeeRequest request2 = new EmployeeRequest("Krishna", "MCA", 20000.0);
		employeeService.registerEmployee(request2);

		EmployeeRequest request3 = new EmployeeRequest("Neha", "Finance", 40000.0);
		employeeService.registerEmployee(request3);

		EmployeeRequest request4 = new EmployeeRequest("Pragya", "BA", 30000.0);
		employeeService.registerEmployee(request4);
	}
}
