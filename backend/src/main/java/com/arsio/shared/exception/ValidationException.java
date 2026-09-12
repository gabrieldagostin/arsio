package com.arsio.shared.exception;

import com.arsio.shared.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ValidationException extends BusinessException {

    protected ValidationException(String message, String errorType, ErrorCode errorCode) {
        super(
                message,
                HttpStatus.BAD_REQUEST,
                errorType,
                errorCode
        );
    }
}
