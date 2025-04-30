package com.doubleo.memberservice.domain.member.dto.response;

import com.doubleo.memberservice.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record MemberCreateResponse(
        @Schema(description = "회원 ID", example = "1") Long memberId,
        @Schema(description = "회원 email", example = "example@gmail.com") String email) {
    public static MemberCreateResponse of(Member member) {
        return new MemberCreateResponse(member.getId(), member.getEmail());
    }
}
