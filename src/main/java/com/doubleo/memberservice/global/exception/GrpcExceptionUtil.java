package com.doubleo.memberservice.global.exception;

import com.doubleo.memberservice.global.exception.errorcode.BaseErrorCode;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public class GrpcExceptionUtil {

    public static StatusRuntimeException toStatusRuntimeException(BaseErrorCode errorCode) {
        Metadata metadata = new Metadata();

        Metadata.Key<String> codeKey = Metadata.Key.of("code", Metadata.ASCII_STRING_MARSHALLER);
        Metadata.Key<String> messageKey =
                Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER);

        metadata.put(codeKey, errorCode.errorClassName());
        metadata.put(messageKey, errorCode.getMessage());

        return Status.fromCodeValue(errorCode.getHttpStatus().value())
                .withDescription(errorCode.getMessage())
                .asRuntimeException(metadata);
    }
}
