package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidGenreNameException extends ValidationException {

    public InvalidGenreNameException() {
        super(
                "Invalid genre name, please try again.",
                ErrorTypes.GAME_INVALID_GENRE_NAME,
                ErrorCode.GAME_INVALID_GENRE_NAME
        );
    }

    public InvalidGenreNameException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_GENRE_NAME,
                ErrorCode.GAME_INVALID_GENRE_NAME
        );
    }
}
