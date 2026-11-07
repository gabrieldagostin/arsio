package com.arsio.cart.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidCartStatusException extends ValidationException {

    public InvalidCartStatusException() {
        super(
                "Invalid cart status, try again.",
                ErrorTypes.CART_INVALID_STATUS,
                ErrorCode.CART_INVALID_STATUS
        );
    }

    public InvalidCartStatusException(String message) {
        super(
                message,
                ErrorTypes.CART_INVALID_STATUS,
                ErrorCode.CART_INVALID_STATUS
        );
    }
}
