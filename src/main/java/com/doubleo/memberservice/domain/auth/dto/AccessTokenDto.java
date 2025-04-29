package com.doubleo.memberservice.domain.auth.dto;


public record AccessTokenDto(Long memberId, String accessTokenValue) {
    public static AccessTokenDto of(Long memberId, String accessTokenValue) {
        return new AccessTokenDto(memberId, accessTokenValue);
    }
}