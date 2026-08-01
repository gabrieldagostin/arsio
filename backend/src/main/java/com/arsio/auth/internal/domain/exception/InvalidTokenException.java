package com.arsio.auth.internal.domain.exception;

import com.arsio.shared.exception.UnauthorizedException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidTokenException extends UnauthorizedException {

    public InvalidTokenException() {
        super(
                "Invalid token.",
                ErrorTypes.AUTH_INVALID_TOKEN,
                ErrorCode.AUTH_INVALID_TOKEN
        );
    }

    public InvalidTokenException(String message) {
        super(
                message,
                ErrorTypes.AUTH_INVALID_TOKEN,
                ErrorCode.AUTH_INVALID_TOKEN
        );
    }
}
