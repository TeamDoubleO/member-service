package com.doubleo.memberservice.domain.member.controller;

import com.doubleo.memberservice.domain.auth.service.AuthService;
import com.doubleo.memberservice.domain.member.dto.request.MemberCreateRequest;
import com.doubleo.memberservice.domain.member.dto.request.MemberPwCheckRequest;
import com.doubleo.memberservice.domain.member.dto.request.MemberPwUpdateRequest;
import com.doubleo.memberservice.domain.member.dto.response.MemberCreateResponse;
import com.doubleo.memberservice.domain.member.dto.response.MemberInfoResponse;
import com.doubleo.memberservice.domain.member.service.MemberService;
import com.doubleo.memberservice.global.util.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "1-1. Member API", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final AuthService authService;
    private final CookieUtil cookieUtil;

    @Operation(summary = "회원 가입", description = "회원을 생성합니다.")
    @PostMapping
    public MemberCreateResponse memberJoin(@Valid @RequestBody MemberCreateRequest request) {
        return memberService.createMember(request);
    }

    @Operation(summary = "회원 본인 정보 조회", description = "회원 본인 정보를 조회합니다.")
    @GetMapping("/me")
    public MemberInfoResponse memberGet(@RequestHeader("X-Member-Id") Long memberId) {
        return memberService.getMemberInfo(memberId);
    }

    @Operation(summary = "회원 비밀번호 업데이트", description = "회원 비밀번호를 업데이트합니다.")
    @PatchMapping("/me/password")
    public ResponseEntity<Void> memberPasswordUpdate(
            @RequestHeader("X-Member-Id") Long memberId,
            @Valid @RequestBody MemberPwUpdateRequest request) {
        memberService.updateMemberPassword(memberId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 비밀번호 화인", description = "my page 진입 시 회원 비밀번호를 확인합니다.")
    @PostMapping("/me/password")
    public ResponseEntity<Void> memberPasswordCheck(
            @RequestHeader("X-Member-Id") Long memberId,
            @Valid @RequestBody MemberPwCheckRequest request) {
        memberService.checkMemberPassword(memberId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 탈퇴", description = "회원 정보를 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<Void> memberDelete(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestHeader("X-Member-Id") Long memberId,
            HttpServletResponse response) {
        authService.logoutMember(authorizationHeader, memberId);
        memberService.deleteMember(memberId);
        HttpHeaders headers = cookieUtil.deleteRefreshTokenCookie();
        response.addHeader(HttpHeaders.SET_COOKIE, headers.getFirst(HttpHeaders.SET_COOKIE));

        return ResponseEntity.ok().build();
    }
}
