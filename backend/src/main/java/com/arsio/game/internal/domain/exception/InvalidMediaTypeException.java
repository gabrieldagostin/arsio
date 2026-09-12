package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidMediaTypeException extends ValidationException {

    public InvalidMediaTypeException() {
        super(
                "Invalid media type, please try again.",
                ErrorTypes.GAME_INVALID_MEDIA_TYPE,
                ErrorCode.GAME_INVALID_MEDIA_TYPE
        );
    }

    public InvalidMediaTypeException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_MEDIA_TYPE,
                ErrorCode.GAME_INVALID_MEDIA_TYPE
        );
    }
}
