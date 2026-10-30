package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidGameFileSizeException extends ValidationException {

    public InvalidGameFileSizeException() {
        super(
                "Invalid game file size, please try again.",
                ErrorTypes.GAME_INVALID_FILE_SIZE,
                ErrorCode.GAME_INVALID_FILE_SIZE
        );
    }

    public InvalidGameFileSizeException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_FILE_SIZE,
                ErrorCode.GAME_INVALID_FILE_SIZE
        );
    }
}
