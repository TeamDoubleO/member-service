package com.doubleo.memberservice.domain.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import com.doubleo.memberservice.domain.member.domain.Member;
import com.doubleo.memberservice.domain.member.dto.request.MemberCreateRequest;
import com.doubleo.memberservice.domain.member.dto.request.MemberPwUpdateRequest;
import com.doubleo.memberservice.domain.member.dto.response.MemberCreateResponse;
import com.doubleo.memberservice.domain.member.dto.response.MemberInfoResponse;
import com.doubleo.memberservice.domain.member.repository.MemberRepository;
import com.doubleo.memberservice.global.exception.CommonException;
import com.doubleo.memberservice.global.exception.errorcode.MemberErrorCode;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks private MemberServiceImpl memberService;

    @Mock private MemberRepository memberRepository;

    @Mock private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final String email = "test@test.com";
    private final String password = "password";
    private final String name = "name";
    private final String regNo = "991111-1234567";
    private final String contact = "contact";

    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.createMember(email, "encoded", name, regNo, contact);
        ReflectionTestUtils.setField(member, "id", 1L);
    }

    @Nested
    class createMember {

        @Test
        void 회원을_생성하면_정상적으로_생성된다() {
            // given
            MemberCreateRequest request =
                    new MemberCreateRequest(email, password, name, regNo, contact);
            String encodedPassword = "encodedPassword";
            Member savedMember = Member.createMember(email, encodedPassword, name, regNo, contact);

            given(bCryptPasswordEncoder.encode(password)).willReturn(encodedPassword);
            given(memberRepository.save(any(Member.class))).willReturn(savedMember);

            // when
            MemberCreateResponse response = memberService.createMember(request);

            // then
            assertThat(response).isNotNull();
            assertThat(response.email()).isEqualTo(email);
            verify(memberRepository).save(any(Member.class));
        }
    }

    @Nested
    class getMemberInfo {

        @Test
        void 회원정보_조회하면_정상적으로_반환된다() {
            // given
            given(memberRepository.findById(1L)).willReturn(Optional.of(member));

            // when
            MemberInfoResponse response = memberService.getMemberInfo(member.getId());

            // then
            assertThat(response.memberId()).isEqualTo(member.getId());
            assertThat(response.email()).isEqualTo(member.getEmail());
            assertThat(response.name()).isEqualTo(member.getName());
            assertThat(response.regNo()).isEqualTo(member.getRegNo());
            assertThat(response.contact()).isEqualTo(member.getContact());
        }
    }

    @Nested
    class updateMemberPassword {

        @Test
        void 비밀번호_변경하면_정상적으로_변경된다() {
            // given
            String newPassword = "newPassword";
            String encodedNewPassword = "encodedNew";

            given(memberRepository.findById(1L)).willReturn(Optional.of(member));
            given(bCryptPasswordEncoder.matches(password, "encoded")).willReturn(true);
            given(bCryptPasswordEncoder.encode(newPassword)).willReturn(encodedNewPassword);

            // when
            memberService.updateMemberPassword(
                    1L, new MemberPwUpdateRequest(password, newPassword));

            // then
            assertThat(member.getPassword()).isEqualTo(encodedNewPassword);
        }

        @Test
        void 기존_비밀번호_유효하지_않으면_오류_발생한다() {
            // given
            given(memberRepository.findById(1L)).willReturn(Optional.of(member));
            given(bCryptPasswordEncoder.matches("wrongPassword", "encoded")).willReturn(false);

            // when & then
            assertThatThrownBy(
                            () ->
                                    memberService.updateMemberPassword(
                                            1L,
                                            new MemberPwUpdateRequest(
                                                    "wrongPassword", "newPassword")))
                    .isInstanceOf(CommonException.class)
                    .hasMessage(MemberErrorCode.INVALID_PASSWORD.getMessage());
        }

        @Test
        void 기존_비밀번호와_신규_비밀번호가_동일하면_오류_발생한다() {
            // given
            given(memberRepository.findById(1L)).willReturn(Optional.of(member));
            given(bCryptPasswordEncoder.matches(password, "encoded")).willReturn(true);

            // when & then
            assertThatThrownBy(
                            () ->
                                    memberService.updateMemberPassword(
                                            1L, new MemberPwUpdateRequest(password, password)))
                    .isInstanceOf(CommonException.class)
                    .hasMessage(MemberErrorCode.DUPLICATED_PASSWORD.getMessage());
        }
    }
}
