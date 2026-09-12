package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidRequirementException extends ValidationException {

    public InvalidRequirementException() {
        super(
                "Invalid requirements for the given game, please try again.",
                ErrorTypes.GAME_INVALID_REQUIREMENT,
                ErrorCode.GAME_INVALID_REQUIREMENT
        );
    }

    public InvalidRequirementException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_REQUIREMENT,
                ErrorCode.GAME_INVALID_REQUIREMENT
        );
    }
}
