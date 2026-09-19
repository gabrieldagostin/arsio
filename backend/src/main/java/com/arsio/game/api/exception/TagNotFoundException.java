package com.arsio.game.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class TagNotFoundException extends NotFoundException {

    public TagNotFoundException() {
        super(
                "Tag not found,  please try again.",
                ErrorTypes.GAME_TAG_NOT_FOUND,
                ErrorCode.GAME_TAG_NOT_FOUND
        );
    }

    public TagNotFoundException(String message) {
        super(
                message,
                ErrorTypes.GAME_TAG_NOT_FOUND,
                ErrorCode.GAME_TAG_NOT_FOUND
        );
    }
}
