package com.arsio.auth.internal.domain.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class SessionNotFoundException extends NotFoundException {

    public SessionNotFoundException() {
        super(
                "Session not found.",
                ErrorTypes.AUTH_SESSION_NOT_FOUND,
                ErrorCode.AUTH_SESSION_NOT_FOUND
        );
    }

    public SessionNotFoundException(String message) {
        super(
                message,
                ErrorTypes.AUTH_SESSION_NOT_FOUND,
                ErrorCode.AUTH_SESSION_NOT_FOUND
        );
    }
}
