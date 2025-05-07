package com.doubleo.memberservice.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record MemberPwCheckRequest(
        @Schema(description = "회원 패스워드", example = "pw12345") @NotBlank(message = "비밀번호는 필수입니다.")
                String password) {}
