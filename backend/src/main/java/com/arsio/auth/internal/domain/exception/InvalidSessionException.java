package com.arsio.auth.internal.domain.exception;

import com.arsio.shared.exception.UnauthorizedException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidSessionException extends UnauthorizedException {

    public InvalidSessionException() {
        super(
                "Invalid session, please try again.",
                ErrorTypes.AUTH_INVALID_SESSION,
                ErrorCode.AUTH_INVALID_SESSION
        );
    }

    public InvalidSessionException(String message) {
        super(
                message,
                ErrorTypes.AUTH_INVALID_SESSION,
                ErrorCode.AUTH_INVALID_SESSION
        );
    }
}
