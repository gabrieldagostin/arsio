package com.arsio.game.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class GameNotFoundException extends NotFoundException {

    public GameNotFoundException() {
        super(
                "Game not found,  please try again.",
                ErrorTypes.GAME_NOT_FOUND,
                ErrorCode.GAME_NOT_FOUND
        );
    }

    public GameNotFoundException(String message) {
        super(
                message,
                ErrorTypes.GAME_NOT_FOUND,
                ErrorCode.GAME_NOT_FOUND
        );
    }
}
