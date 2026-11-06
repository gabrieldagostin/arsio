package com.arsio.wishlist.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class WishlistNotFoundException extends NotFoundException {

    public WishlistNotFoundException() {
        super(
                "Wishlist not found.",
                ErrorTypes.WISHLIST_NOT_FOUND,
                ErrorCode.WISHLIST_NOT_FOUND
        );
    }

    public WishlistNotFoundException(String message) {
        super(
                message,
                ErrorTypes.WISHLIST_NOT_FOUND,
                ErrorCode.WISHLIST_NOT_FOUND
        );
    }
}