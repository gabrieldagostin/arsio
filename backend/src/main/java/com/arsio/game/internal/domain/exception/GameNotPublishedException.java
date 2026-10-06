package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class GameNotPublishedException extends ValidationException {

    public GameNotPublishedException() {
        super(
                "Invalid game status, please try again.",
                ErrorTypes.GAME_NOT_PUBLISHED,
                ErrorCode.GAME_NOT_PUBLISHED
        );
    }

    public GameNotPublishedException(String message) {
        super(
                message,
                ErrorTypes.GAME_NOT_PUBLISHED,
                ErrorCode.GAME_NOT_PUBLISHED
        );
    }
}
