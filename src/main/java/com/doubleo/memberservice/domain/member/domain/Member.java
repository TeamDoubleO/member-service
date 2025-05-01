package com.doubleo.memberservice.domain.member.domain;

import com.doubleo.memberservice.domain.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "member_email", nullable = false, unique = true)
    private String email; // 정규화 추가

    @Column(name = "member_password", nullable = false, length = 100)
    private String password;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_reg_no", nullable = false, unique = true)
    private String regNo;

    @Column(name = "member_contact", nullable = false)
    private String contact;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(String email, String password, String regNo, String name, String contact) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
        this.contact = contact;
    }

    public static Member createMember(
            String email, String password, String name, String regNo, String contact) {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .regNo(regNo)
                .contact(contact)
                .build();
    }

    public void updateMemberPassword(String passwordNew) {
        this.password = passwordNew;
    }
}
