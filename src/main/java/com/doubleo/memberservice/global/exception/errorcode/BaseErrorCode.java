package com.doubleo.memberservice.global.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus getHttpStatus();

    String getMessage();

    String errorClassName();
}
