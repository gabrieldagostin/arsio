package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidPasswordException extends ValidationException {

    public InvalidPasswordException() {
        super(
                "Invalid Password",
                ErrorTypes.USER_INVALID_PASSWORD,
                ErrorCode.USER_INVALID_PASSWORD
        );
    }

    public InvalidPasswordException(String message) {
        super(
                message,
                ErrorTypes.USER_INVALID_PASSWORD,
                ErrorCode.USER_INVALID_PASSWORD
        );
    }
}
