package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidUserRoleException extends ValidationException {

    public InvalidUserRoleException() {
        super(
                "Invalid user role, please try again.",
                ErrorTypes.USER_INVALID_USER_ROLE,
                ErrorCode.USER_INVALID_USER_ROLE
        );
    }

    public InvalidUserRoleException(String message) {
        super(
                message,
                ErrorTypes.USER_INVALID_USER_ROLE,
                ErrorCode.USER_INVALID_USER_ROLE
        );
    }
}
