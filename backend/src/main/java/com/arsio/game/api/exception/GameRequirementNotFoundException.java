package com.arsio.game.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class GameRequirementNotFoundException extends NotFoundException {

    public GameRequirementNotFoundException() {
        super(
                "Game requirement not found,  please try again.",
                ErrorTypes.GAME_REQUIREMENT_NOT_FOUND,
                ErrorCode.GAME_REQUIREMENT_NOT_FOUND
        );
    }

    public GameRequirementNotFoundException(String message) {
        super(
                message,
                ErrorTypes.GAME_REQUIREMENT_NOT_FOUND,
                ErrorCode.GAME_REQUIREMENT_NOT_FOUND
        );
    }
}
