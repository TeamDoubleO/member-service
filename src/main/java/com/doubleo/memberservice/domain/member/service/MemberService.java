package com.doubleo.memberservice.domain.member.service;

import com.doubleo.memberservice.domain.member.dto.request.MemberCreateRequest;
import com.doubleo.memberservice.domain.member.dto.request.MemberPwUpdateRequest;
import com.doubleo.memberservice.domain.member.dto.response.MemberCreateResponse;
import com.doubleo.memberservice.domain.member.dto.response.MemberInfoResponse;

public interface MemberService {

    MemberCreateResponse createMember(MemberCreateRequest request);

    MemberInfoResponse getMemberInfo(Long memberId);

    void updateMemberPassword(MemberPwUpdateRequest request);
}
