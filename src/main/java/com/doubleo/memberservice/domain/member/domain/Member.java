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

    @Column(name = "member_birth_date", nullable = false)
    private String birthDate;

    @Column(name = "member_contact", nullable = false)
    private String contact;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(
            String email,
            String password,
            String regNo,
            String birthDate,
            String name,
            String contact) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.regNo = regNo;
        this.birthDate = birthDate;
        this.contact = contact;
    }

    public static Member createMember(
            String email, String password, String name, String regNo, String contact) {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .regNo(regNo)
                .birthDate(extractBirthDateFromRegNo(regNo))
                .contact(contact)
                .build();
    }

    public void updateMemberPassword(String passwordNew) {
        this.password = passwordNew;
    }

    private static String extractBirthDateFromRegNo(String regNo) {
        if (regNo == null || regNo.length() != 14 || regNo.charAt(6) != '-') {
            return "NULL";
        }
        String[] parts = regNo.split("-");
        String birth = parts[0];
        char genderCode = parts[1].charAt(0);

        String century;
        switch (genderCode) {
            case '1':
            case '2':
            case '5':
            case '6':
                century = "19";
                break;
            case '3':
            case '4':
            case '7':
            case '8':
                century = "20";
                break;
            default:
                return "NULL";
        }
        return century
                + birth.substring(0, 2)
                + "-"
                + birth.substring(2, 4)
                + "-"
                + birth.substring(4, 6);
    }
}
