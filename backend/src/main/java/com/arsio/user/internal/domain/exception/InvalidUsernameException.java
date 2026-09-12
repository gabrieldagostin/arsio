package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidUsernameException extends ValidationException {

    public InvalidUsernameException() {
        super(
                "Invalid username, please try again.",
                ErrorTypes.USER_INVALID_USERNAME,
                ErrorCode.USER_INVALID_USERNAME
        );
    }

    public InvalidUsernameException(String message) {
        super(
                message,
                ErrorTypes.USER_INVALID_USERNAME,
                ErrorCode.USER_INVALID_USERNAME
        );
    }
}
