package com.doubleo.memberservice.domain.member.service;

import com.doubleo.memberservice.domain.member.domain.Member;
import com.doubleo.memberservice.domain.member.dto.request.MemberCreateRequest;
import com.doubleo.memberservice.domain.member.dto.request.MemberPwUpdateRequest;
import com.doubleo.memberservice.domain.member.dto.response.MemberCreateResponse;
import com.doubleo.memberservice.domain.member.dto.response.MemberInfoResponse;
import com.doubleo.memberservice.domain.member.repository.MemberRepository;
import com.doubleo.memberservice.global.exception.CommonException;
import com.doubleo.memberservice.global.exception.errorcode.MemberErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public MemberCreateResponse createMember(MemberCreateRequest request) {
        validateMember(request.email());
        String encodedPw = passwordEncoder.encode(request.password());
        Member member =
                memberRepository.save(
                        Member.createMember(
                                request.email(),
                                encodedPw,
                                request.name(),
                                request.regNo(),
                                request.contact()));
        return MemberCreateResponse.of(member);
    }

    @Override
    public MemberInfoResponse getMemberInfo(Long memberId) {
        Member member = findMember(memberId);
        return MemberInfoResponse.of(member);
    }

    @Override
    public void updateMemberPassword(Long memberId, MemberPwUpdateRequest request) {
        Member member = findMember(memberId);
        validateMemberPassword(request.passwordOriginal(), member.getPassword());
        isPasswordNew(request.passwordNew(), member.getPassword());
        member.updateMemberPassword(passwordEncoder.encode(request.passwordNew()));
    }

    // util
    private Member findMember(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new CommonException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    private void validateMember(String email) {
        if (memberRepository.findMemberByEmail(email).isPresent()) {
            throw new CommonException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }
    }

    private void validateMemberPassword(String raw, String encoded) {
        if (!passwordEncoder.matches(raw, encoded)) {
            throw new CommonException(MemberErrorCode.INVALID_PASSWORD);
        }
    }

    private void isPasswordNew(String raw, String encoded) {
        if (passwordEncoder.matches(raw, encoded)) {
            throw new CommonException(MemberErrorCode.DUPLICATED_PASSWORD);
        }
    }
}
