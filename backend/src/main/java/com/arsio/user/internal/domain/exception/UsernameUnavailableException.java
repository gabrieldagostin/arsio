package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ConflictException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class UsernameUnavailableException extends ConflictException {

    public UsernameUnavailableException() {
        super(
                "Username unavailable, please try again.",
                ErrorTypes.USER_USERNAME_UNAVAILABLE,
                ErrorCode.USER_USERNAME_UNAVAILABLE
        );
    }

    public UsernameUnavailableException(String username) {
        super(
                "Username %s unavailable, please try again.".formatted(username),
                ErrorTypes.USER_USERNAME_UNAVAILABLE,
                ErrorCode.USER_USERNAME_UNAVAILABLE
        );
    }
}
