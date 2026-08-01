package com.arsio.shared.exception;

import com.arsio.shared.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BusinessException{
    protected UnauthorizedException(String message, String errorType, ErrorCode errorCode) {
        super(
                message,
                HttpStatus.UNAUTHORIZED,
                errorType,
                errorCode
        );
    }
}
