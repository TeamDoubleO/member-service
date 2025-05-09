package com.doubleo.memberservice.global.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Platform {
    MOBILE("MOBILE");

    private final String platform;
}
