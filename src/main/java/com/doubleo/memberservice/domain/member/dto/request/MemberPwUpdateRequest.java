package com.doubleo.memberservice.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MemberPwUpdateRequest(
        @Schema(description = "회원 id", example = "1") @NotNull Long memberId,
        @Schema(description = "회원 기존 패스워드", example = "pw12345")
                @NotBlank(message = "기존 비밀번호는 필수입니다.")
                String passwordOriginal,
        @Schema(description = "회원 신규 패스워드", example = "pw67890")
                @NotBlank(message = "신규 비밀번호는 필수입니다.")
                String passwordNew) {}
