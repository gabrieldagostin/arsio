package com.arsio.game.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class DiscountNotFoundException extends NotFoundException {

    public DiscountNotFoundException() {
        super(
                "Discount not found.",
                ErrorTypes.GAME_DISCOUNT_NOT_FOUND,
                ErrorCode.GAME_DISCOUNT_NOT_FOUND
        );
    }

    public DiscountNotFoundException(String message) {
        super(
                message,
                ErrorTypes.GAME_DISCOUNT_NOT_FOUND,
                ErrorCode.GAME_DISCOUNT_NOT_FOUND
        );
    }
}
