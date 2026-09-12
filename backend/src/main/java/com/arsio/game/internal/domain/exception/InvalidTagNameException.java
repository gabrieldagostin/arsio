package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidTagNameException extends ValidationException {

    public InvalidTagNameException() {
        super(
                "Invalid requirements for the given game, please try again.",
                ErrorTypes.GAME_INVALID_TAG_NAME,
                ErrorCode.GAME_INVALID_TAG_NAME
        );
    }

    public InvalidTagNameException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_TAG_NAME,
                ErrorCode.GAME_INVALID_TAG_NAME
        );
    }
}
