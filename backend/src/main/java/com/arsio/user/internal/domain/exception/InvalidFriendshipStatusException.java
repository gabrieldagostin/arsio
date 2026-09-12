package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;


public class InvalidFriendshipStatusException extends ValidationException {

    public InvalidFriendshipStatusException() {
        super(
                "Invalid friendship status, please try again.",
                ErrorTypes.USER_INVALID_FRIENDSHIP_STATUS,
                ErrorCode.USER_INVALID_FRIENDSHIP_STATUS
        );
    }

    public InvalidFriendshipStatusException(String message) {
        super(
                message,
                ErrorTypes.USER_INVALID_FRIENDSHIP_STATUS,
                ErrorCode.USER_INVALID_FRIENDSHIP_STATUS
        );
    }
}
