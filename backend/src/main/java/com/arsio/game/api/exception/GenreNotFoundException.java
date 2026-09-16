package com.arsio.game.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class GenreNotFoundException extends NotFoundException {

    public GenreNotFoundException() {
        super(
                "Genre not found,  please try again.",
                ErrorTypes.GAME_GENRE_NOT_FOUND,
                ErrorCode.GAME_GENRE_NOT_FOUND
        );
    }

    public GenreNotFoundException(String message) {
        super(
                message,
                ErrorTypes.GAME_GENRE_NOT_FOUND,
                ErrorCode.GAME_GENRE_NOT_FOUND
        );
    }
}
