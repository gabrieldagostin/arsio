package com.arsio.shared.exception;

import com.arsio.shared.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends BusinessException {
    protected ForbiddenException(String message, String errorType, ErrorCode errorCode) {
        super(
                message,
                HttpStatus.FORBIDDEN,
                errorType,
                errorCode
        );
    }

    public ForbiddenException() {}
}
