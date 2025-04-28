package com.doubleo.memberservice.global.exception;

public record ErrorResponse(String errorClassName, String message) {
    public static ErrorResponse of(String errorClassName, String message) {
        return new ErrorResponse(errorClassName, message);
    }
}
