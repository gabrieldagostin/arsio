package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidDescriptionException extends ValidationException {

    public InvalidDescriptionException() {
        super(
                "Description length exceeds 1000 characters",
                ErrorTypes.GAME_INVALID_DESCRIPTION,
                ErrorCode.GAME_INVALID_DESCRIPTION
        );
    }

    public InvalidDescriptionException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_DESCRIPTION,
                ErrorCode.GAME_INVALID_DESCRIPTION
        );
    }
}
