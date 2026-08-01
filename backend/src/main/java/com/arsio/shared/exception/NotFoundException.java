package com.arsio.shared.exception;

import com.arsio.shared.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException {
    protected NotFoundException(String message, String errorType, ErrorCode errorCode) {
        super(
                message,
                HttpStatus.NOT_FOUND,
                errorType,
                errorCode
        );
    }
}
