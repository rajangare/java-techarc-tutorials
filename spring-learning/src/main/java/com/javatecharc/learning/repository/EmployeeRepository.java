package com.javatecharc.learning.repository;

import com.javatecharc.learning.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class EmployeeRepository implements JpaRepository<Employee, Long> {
    // This class is intentionally left empty. It serves as a marker interface for Spring Data JPA.
    // You can add custom query methods here if needed.
}
