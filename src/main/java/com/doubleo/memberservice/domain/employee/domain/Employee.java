package com.doubleo.memberservice.domain.employee.domain;

import com.doubleo.memberservice.domain.common.model.BaseTimeEntity;
import com.doubleo.memberservice.domain.department.domain.Department;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Employee extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "employee_email")
    private String email; // 정규화 추가

    @Column(name = "employee_password")
    private String password;

    @Column(name = "employee_id_number")
    private String idNumber; // 정규화 추가

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_name_eng")
    private String nameEng;

    @JoinColumn(name = "employee_department")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @Column(name = "employee_position")
    private String position; //

    @Column(name = "employee_profile_image_url")
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status")
    private EmployeeStatus status; // 재직상태

    @Builder(access = AccessLevel.PRIVATE)
    private Employee(
            String email,
            String password,
            String idNumber,
            String name,
            String nameEng,
            Department department,
            String position,
            String profileImageUrl,
            EmployeeStatus status) {
        this.email = email;
        this.password = password;
        this.idNumber = idNumber;
        this.name = name;
        this.nameEng = nameEng;
        this.department = department;
        this.position = position;
        this.profileImageUrl = profileImageUrl;
        this.status = status;
    }

    public static Employee createEmployee(
            String email,
            String password,
            String idNumber,
            String name,
            String nameEng,
            Department department,
            String position,
            String profileImageUrl) {
        return Employee.builder()
                .email(email)
                .password(password)
                .idNumber(idNumber)
                .name(name)
                .nameEng(nameEng)
                .department(department)
                .position(position)
                .profileImageUrl(profileImageUrl)
                .status(EmployeeStatus.ON_DUTY)
                .build();
    }
}
