package com.arsio.game.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class GameMediaNotFoundException extends NotFoundException {

    public GameMediaNotFoundException() {
        super(
                "Game media not found,  please try again.",
                ErrorTypes.GAME_MEDIA_NOT_FOUND,
                ErrorCode.GAME_MEDIA_NOT_FOUND
        );
    }

    public GameMediaNotFoundException(String message) {
        super(
                message,
                ErrorTypes.GAME_MEDIA_NOT_FOUND,
                ErrorCode.GAME_MEDIA_NOT_FOUND
        );
    }
}
