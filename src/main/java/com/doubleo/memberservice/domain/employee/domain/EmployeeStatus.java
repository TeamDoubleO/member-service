package com.doubleo.memberservice.domain.employee.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmployeeStatus {
    ON_DUTY("EMPLOYEE_ON_DUTY"),
    ON_LEAVE("EMPLOYEE_ON_LEAVE"),
    RETIRED("EMPLOYEE_RETIRED"),
    ;

    private final String status;
}
