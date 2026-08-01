package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ConflictException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class EmailAlreadyExistsException extends ConflictException {

    public EmailAlreadyExistsException() {
        super(
                "Email already belongs to another user.",
                ErrorTypes.USER_EMAIL_ALREADY_EXISTS,
                ErrorCode.USER_EMAIL_ALREADY_EXISTS
        );
    }

    public EmailAlreadyExistsException(String message) {
        super(
                message,
                ErrorTypes.USER_EMAIL_ALREADY_EXISTS,
                ErrorCode.USER_EMAIL_ALREADY_EXISTS
        );
    }
}
