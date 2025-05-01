package com.doubleo.memberservice.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record MemberCreateRequest(
        @Schema(description = "회원 email", example = "example@gmail.com")
                @NotBlank(message = "회원 이메일은 필수입니다.")
                String email,
        @Schema(description = "회원 패스워드", example = "pw12345") @NotBlank(message = "회원 비밀번호는 필수입니다.")
                String password,
        @Schema(description = "회원 이름", example = "정선우") @NotBlank(message = "회원 이름은 필수입니다.")
                String name,
        @Schema(description = "회원 주민등록번호", example = "990101-1234567")
                @NotBlank(message = "회원 주민등록번호는 필수입니다.")
                String regNo,
        @Schema(description = "회원 연락처", example = "010-1234-5678")
                @NotBlank(message = "회원 연락처는 필수입니다.")
                String contact) {}
