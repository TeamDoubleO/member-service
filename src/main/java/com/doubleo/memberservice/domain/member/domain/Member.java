package com.doubleo.memberservice.domain.member.domain;

import com.doubleo.memberservice.domain.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "`member`")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email")
    private String email; // 정규화 추가

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_reg_no")
    private String regNo;

    @Column(name = "member_contact")
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
}
