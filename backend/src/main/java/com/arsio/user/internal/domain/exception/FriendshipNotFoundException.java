package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class FriendshipNotFoundException extends NotFoundException {

    public FriendshipNotFoundException() {
        super(
                "Friendship not found, please try again.",
                ErrorTypes.USER_FRIENDSHIP_NOT_FOUND,
                ErrorCode.USER_FRIENDSHIP_NOT_FOUND
        );
    }

    public FriendshipNotFoundException(String message) {
        super(
                message,
                ErrorTypes.USER_FRIENDSHIP_NOT_FOUND,
                ErrorCode.USER_FRIENDSHIP_NOT_FOUND
        );
    }
}
