package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidGameVersionException extends ValidationException {

    public InvalidGameVersionException() {
        super(
                "Invalid game version, try again.",
                ErrorTypes.GAME_INVALID_VERSION,
                ErrorCode.GAME_INVALID_VERSION
        );
    }

    public InvalidGameVersionException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_VERSION,
                ErrorCode.GAME_INVALID_VERSION
        );
    }
}
