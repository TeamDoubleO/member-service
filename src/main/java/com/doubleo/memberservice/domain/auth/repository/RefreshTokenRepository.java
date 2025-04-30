package com.doubleo.memberservice.domain.auth.repository;

import com.doubleo.memberservice.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {}
