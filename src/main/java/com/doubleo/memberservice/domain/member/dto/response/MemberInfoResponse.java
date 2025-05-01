package com.doubleo.memberservice.domain.member.dto.response;

import com.doubleo.memberservice.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record MemberInfoResponse(
        @Schema(description = "회원 id", example = "example@gmail.com") Long memberId,
        @Schema(description = "회원 email", example = "example@gmail.com") String email,
        @Schema(description = "회원 이름", example = "정선우") String name,
        @Schema(description = "회원 주민등록번호", example = "990101-1234567") String regNo,
        @Schema(description = "회원 연락처", example = "010-1234-5678") String contact) {
    public static MemberInfoResponse of(Member member) {
        return new MemberInfoResponse(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getRegNo(),
                member.getContact());
    }
}
