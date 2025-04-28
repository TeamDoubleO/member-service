package com.doubleo.memberservice.domain.member.repository;

import com.doubleo.memberservice.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
