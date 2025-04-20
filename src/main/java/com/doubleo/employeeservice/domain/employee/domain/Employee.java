package com.doubleo.employeeservice.domain.employee.domain;

import com.doubleo.employeeservice.domain.common.model.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
public class Employee extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
}
