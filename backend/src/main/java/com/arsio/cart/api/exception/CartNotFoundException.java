package com.arsio.cart.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class CartNotFoundException extends NotFoundException {

    public CartNotFoundException() {
        super(
                "Cart not found.",
                ErrorTypes.CART_NOT_FOUND,
                ErrorCode.CART_NOT_FOUND
        );
    }

    public CartNotFoundException(String message) {
        super(
                message,
                ErrorTypes.CART_NOT_FOUND,
                ErrorCode.CART_NOT_FOUND
        );
    }
}
