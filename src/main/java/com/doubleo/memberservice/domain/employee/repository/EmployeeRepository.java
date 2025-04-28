package com.doubleo.memberservice.domain.employee.repository;

import com.doubleo.memberservice.domain.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
