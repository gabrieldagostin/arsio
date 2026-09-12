package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidFriendshipException extends ValidationException {

    public InvalidFriendshipException() {
        super(
                "Invalid friendship, please try again.",
                ErrorTypes.USER_INVALID_FRIENDSHIP,
                ErrorCode.USER_INVALID_FRIENDSHIP
        );
    }

    public InvalidFriendshipException(String message) {
        super(
                message,
                ErrorTypes.USER_INVALID_FRIENDSHIP,
                ErrorCode.USER_INVALID_FRIENDSHIP
        );
    }
}
