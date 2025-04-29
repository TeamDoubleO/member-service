package com.doubleo.memberservice.domain.auth.repository;

import com.doubleo.memberservice.domain.auth.domain.BlackListToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListTokenRepository extends CrudRepository<BlackListToken, String> {
    // existsById(token) 으로 블랙리스트 조회
}
