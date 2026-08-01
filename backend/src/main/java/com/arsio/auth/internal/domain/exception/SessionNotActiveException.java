package com.arsio.auth.internal.domain.exception;

import com.arsio.shared.exception.UnauthorizedException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class SessionNotActiveException extends UnauthorizedException {

    public SessionNotActiveException() {
        super(
                "Session is expired or revoked.",
                ErrorTypes.AUTH_SESSION_NOT_ACTIVE,
                ErrorCode.AUTH_SESSION_NOT_ACTIVE
        );
    }

    public SessionNotActiveException(String message) {
        super(
                message,
                ErrorTypes.AUTH_SESSION_NOT_ACTIVE,
                ErrorCode.AUTH_SESSION_NOT_ACTIVE
        );
    }
}
