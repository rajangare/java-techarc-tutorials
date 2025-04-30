package com.javatecharc.learning.repository;

import com.javatecharc.learning.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Employee entity.
 * Provides CRUD operations and custom query methods through JpaRepository.
 */
@Repository // Indicates that this interface is a Spring Data repository.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // This class is intentionally left empty. It serves as a marker interface for Spring Data JPA.
    // You can add custom query methods here if needed.

    Employee findByName(String name);// Custom query method to find an employee by name.

    @Query(nativeQuery = true, value = "SELECT * FROM EMPLOYEE WHERE SALARY > ?")
    Employee findSalaryGreaterThan(Double salary);// Custom query method to find an employee with a salary greater than the specified amount.
}
