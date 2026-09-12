package com.arsio.auth.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidCredentialException extends ValidationException {

    public InvalidCredentialException() {
        super(
                "Invalid username or password, please try again.",
                ErrorTypes.AUTH_INVALID_CREDENTIALS,
                ErrorCode.AUTH_INVALID_CREDENTIALS
        );
    }

    public InvalidCredentialException(String message) {
        super(
                message,
                ErrorTypes.AUTH_INVALID_CREDENTIALS,
                ErrorCode.AUTH_INVALID_CREDENTIALS
        );
    }
}
