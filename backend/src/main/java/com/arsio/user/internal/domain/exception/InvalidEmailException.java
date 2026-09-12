package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidEmailException extends ValidationException {

    public InvalidEmailException() {
        super(
                "Email malformed, please try again.",
                ErrorTypes.USER_EMAIL_MALFORMED,
                ErrorCode.USER_EMAIL_MALFORMED
        );
    }

    public InvalidEmailException(String message) {
        super(
                message,
                ErrorTypes.USER_EMAIL_MALFORMED,
                ErrorCode.USER_EMAIL_MALFORMED
        );
    }
}
