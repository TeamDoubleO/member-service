package com.doubleo.memberservice.domain.member.service;

import com.doubleo.memberservice.domain.member.dto.request.MemberCreateRequest;
import com.doubleo.memberservice.domain.member.dto.request.MemberPwUpdateRequest;
import com.doubleo.memberservice.domain.member.dto.response.MemberCreateResponse;

public interface MemberService {

    MemberCreateResponse createMember(MemberCreateRequest request);

    void updateMemberPassword(MemberPwUpdateRequest request);
}
