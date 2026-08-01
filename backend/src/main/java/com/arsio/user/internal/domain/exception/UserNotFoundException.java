package com.arsio.user.internal.domain.exception;

import com.arsio.shared.exception.enums.ErrorCode;
import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super(
                "The requested user not exists.",
                ErrorTypes.USER_NOT_FOUND,
                ErrorCode.USER_NOT_FOUND
        );
    }

    public UserNotFoundException(String username) {
        super(
                "The requested user %s not exists.".formatted(username),
                ErrorTypes.USER_NOT_FOUND,
                ErrorCode.USER_NOT_FOUND
        );
    }

    public UserNotFoundException(UUID uuid) {
        super(
                "The requested user on id %s not exists.".formatted(uuid),
                ErrorTypes.USER_NOT_FOUND,
                ErrorCode.USER_NOT_FOUND
        );
    }
}
