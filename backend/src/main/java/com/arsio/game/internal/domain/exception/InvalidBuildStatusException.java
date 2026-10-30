package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidBuildStatusException extends ValidationException {

    public InvalidBuildStatusException() {
        super(
                "Invalid build status, please try again.",
                ErrorTypes.GAME_INVALID_BUILD_STATUS,
                ErrorCode.GAME_INVALID_BUILD_STATUS
        );
    }

    public InvalidBuildStatusException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_BUILD_STATUS,
                ErrorCode.GAME_INVALID_BUILD_STATUS
        );
    }
}
