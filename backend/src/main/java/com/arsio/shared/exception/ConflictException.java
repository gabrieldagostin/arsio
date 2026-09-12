package com.arsio.shared.exception;

import com.arsio.shared.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ConflictException extends BusinessException {

    protected ConflictException(String message, String errorType, ErrorCode errorCode) {
        super(
                message,
                HttpStatus.CONFLICT,
                errorType,
                errorCode
        );
    }
}
