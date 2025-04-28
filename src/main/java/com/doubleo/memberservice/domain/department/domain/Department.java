package com.doubleo.memberservice.domain.department.domain;

import com.doubleo.memberservice.domain.common.model.BaseTimeEntity;
import com.doubleo.memberservice.domain.employee.domain.Employee;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Department extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @Column(name = "department_name")
    private String departmentName; // 정규화 추가

    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Employee> employeeList = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public static Department createDepartment(String departmentName) {
        return Department.builder().departmentName(departmentName).build();
    }
}
