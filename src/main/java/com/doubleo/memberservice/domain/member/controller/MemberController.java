package com.doubleo.memberservice.domain.member.controller;

import com.doubleo.memberservice.domain.member.domain.Member;
import com.doubleo.memberservice.domain.member.dto.request.MemberCreateRequest;
import com.doubleo.memberservice.domain.member.dto.response.MemberCreateResponse;
import com.doubleo.memberservice.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "1-1. Member API", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 가입", description = "회원을 생성합니다.")
    @PostMapping("/join")
    public MemberCreateResponse memberJoin(@RequestBody MemberCreateRequest request) {
        return memberService.createMember(request);
    }

    @Operation(summary = "회원 개별 정보 조회", description = "회원 개별 정보를 조회합니다.")
    @GetMapping("/{memberId}")
    public ResponseEntity<Member> memberGet() {
        return ResponseEntity.ok(new Member());
    }

    // 회원 전체 목록 조회
    //    @GetMapping("/")
    //    public ResponseEntity<Member> memberListGet() {
    //        return ResponseEntity.ok(new Member());
    //    }

    @Operation(summary = "회원 정보 업데이트", description = "회원 정보를 업데이트합니다.")
    @PatchMapping("/{memberId}")
    public ResponseEntity<Member> memberUpdate() {
        return ResponseEntity.ok(new Member());
    }

    // 회원 탈퇴
    @Operation(summary = "회원 탈퇴", description = "회원 정보를 삭제합니다.")
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Member> memberDelete() {
        return ResponseEntity.ok(new Member());
    }
}
