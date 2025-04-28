package com.doubleo.memberservice.domain.employee.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Position {
    POSITION_1("EMPLOYEE_POSITION_1"),
    POSITION_2("EMPLOYEE_POSITION_2"),
    POSITION_3("EMPLOYEE_POSITION_3"),
    ;

    private final String position;
}
