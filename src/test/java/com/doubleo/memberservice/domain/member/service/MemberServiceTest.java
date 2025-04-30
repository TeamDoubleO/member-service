package com.doubleo.memberservice.domain.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.doubleo.memberservice.IntegrationTest;
import com.doubleo.memberservice.domain.member.domain.Member;
import com.doubleo.memberservice.domain.member.dto.request.MemberCreateRequest;
import com.doubleo.memberservice.domain.member.dto.response.MemberCreateResponse;
import com.doubleo.memberservice.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MemberServiceTest extends IntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

    private String email = "test@test.com";
    private String password = "password";
    private String name = "name";
    private String regNo = "991111-1234567";
    private String contact = "contact";

    @BeforeEach
    public void setUp() {}

    @AfterEach
    public void afterEach() {}

    @Nested
    class createMember {

        @Test
        void 회원을_생성하면_정상적으로_생성된다() {
            // given
            String testEmail = email;
            String testPassword = password;
            String testName = name;
            String testRegNo = regNo;
            String testContact = contact;

            // when
            MemberCreateRequest request =
                    new MemberCreateRequest(
                            testEmail, testPassword, testName, testRegNo, testContact);
            MemberCreateResponse response = memberService.createMember(request);

            // then
            Member member = memberRepository.findById(response.memberId()).get();
            assertThat(member.getName()).isEqualTo(name);
            assertThat(member.getEmail()).isEqualTo(testEmail);
            assertThat(bCryptPasswordEncoder.matches(testPassword, member.getPassword())).isTrue();
            assertThat(member.getContact()).isEqualTo(testContact);
            assertThat(member.getRegNo()).isEqualTo(testRegNo);
        }
    }
}
