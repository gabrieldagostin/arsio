package com.arsio.game.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidPercentageException extends ValidationException {

    public InvalidPercentageException() {
        super(
                "Invalid discount percentage, please try again.",
                ErrorTypes.GAME_INVALID_DISCOUNT_PERCENTAGE,
                ErrorCode.GAME_INVALID_DISCOUNT_PERCENTAGE
        );
    }

    public InvalidPercentageException(String message) {
        super(
                message,
                ErrorTypes.GAME_INVALID_DISCOUNT_PERCENTAGE,
                ErrorCode.GAME_INVALID_DISCOUNT_PERCENTAGE
        );
    }
}
