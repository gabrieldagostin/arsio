package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidGameStatusException extends ValidationException {

    public InvalidGameStatusException() {
        super(
                "Invalid game status, please try again.",
                ErrorTypes.GAME_INVALID_STATUS,
                ErrorCode.GAME_INVALID_STATUS
        );
    }

    public InvalidGameStatusException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_STATUS,
                ErrorCode.GAME_INVALID_STATUS
        );
    }
}
