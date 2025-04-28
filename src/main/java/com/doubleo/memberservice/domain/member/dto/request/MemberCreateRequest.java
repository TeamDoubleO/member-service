package com.doubleo.memberservice.domain.member.dto.request;

import com.doubleo.memberservice.domain.department.domain.Department;
import io.swagger.v3.oas.annotations.media.Schema;

public record MemberCreateRequest(
        @Schema(description = "직원 email", example = "sunwoo@lgcns.kr") String email,
        @Schema(description = "직원 ID 번호", example = "12345") String idNumber,
        @Schema(description = "직원 이름", example = "정선우") String name,
        @Schema(description = "직원 영문이름", example = "JUNG SUN WOO") String nameEng,
        @Schema(description = "직원 부서", example = "전략기획실") Department department,
        @Schema(description = "직원 직책", example = "선임") Position position,
        @Schema(description = "직원 프로필이미지", example = "example_url") String ProfileImageUrl,
        @Schema(description = "직원 재직상태", example = "ON_DUTY") EmployeeStatus status) {}
