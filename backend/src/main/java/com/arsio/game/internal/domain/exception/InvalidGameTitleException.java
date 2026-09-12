package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidGameTitleException extends ValidationException {

    public InvalidGameTitleException() {
        super(
                "Invalid game title, please try again.",
                ErrorTypes.GAME_INVALID_TITLE,
                ErrorCode.GAME_INVALID_TITLE
        );
    }

    public InvalidGameTitleException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_TITLE,
                ErrorCode.GAME_INVALID_TITLE
        );
    }
}
