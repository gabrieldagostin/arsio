package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidRequirementCategoryException extends ValidationException {

    public InvalidRequirementCategoryException() {
        super(
                "Invalid requirement category, please try again.",
                ErrorTypes.GAME_INVALID_REQUIREMENT_CATEGORY,
                ErrorCode.GAME_INVALID_REQUIREMENT_CATEGORY
        );
    }

    public InvalidRequirementCategoryException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_REQUIREMENT_CATEGORY,
                ErrorCode.GAME_INVALID_REQUIREMENT_CATEGORY
        );
    }
}
