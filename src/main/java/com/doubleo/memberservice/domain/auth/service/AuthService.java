package com.doubleo.memberservice.domain.auth.service;

import com.doubleo.memberservice.domain.auth.dto.request.LoginRequest;
import com.doubleo.memberservice.domain.auth.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse loginMember(LoginRequest request);

    void logoutMember(String accessTokenValue, Long memberId);
}
