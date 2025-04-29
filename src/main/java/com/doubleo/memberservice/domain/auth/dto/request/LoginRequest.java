package com.doubleo.memberservice.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @Schema(description = "회원 email", example = "example@gmail.com") String email,
        @Schema(description = "회원 패스워드", example = "pw12345") String password) {}
