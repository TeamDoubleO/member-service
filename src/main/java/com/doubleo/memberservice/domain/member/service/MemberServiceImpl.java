package com.doubleo.memberservice.domain.member.service;

import com.doubleo.memberservice.domain.member.domain.Member;
import com.doubleo.memberservice.domain.member.dto.request.MemberCreateRequest;
import com.doubleo.memberservice.domain.member.dto.response.MemberCreateResponse;
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

    private void validateMember(String email) {
        if (memberRepository.findMemberByEmail(email).isPresent()) {
            throw new CommonException(MemberErrorCode.MEMBER_ALREADY_EXIST);
        }
    }
}
