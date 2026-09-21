package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidMediaRoleException extends ValidationException {

    public InvalidMediaRoleException() {
        super(
                "Invalid media role, please try again.",
                ErrorTypes.GAME_INVALID_MEDIA_ROLE,
                ErrorCode.GAME_INVALID_MEDIA_ROLE
        );
    }

    public InvalidMediaRoleException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_MEDIA_ROLE,
                ErrorCode.GAME_INVALID_MEDIA_ROLE
        );
    }
}
