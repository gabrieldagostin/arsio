package com.arsio.review.api.exception;

import com.arsio.shared.exception.NotFoundException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class ReviewNotFoundException extends NotFoundException {

    public ReviewNotFoundException() {
        super(
                "Review not found, try again.",
                ErrorTypes.REVIEW_NOT_FOUND,
                ErrorCode.REVIEW_NOT_FOUND
        );
    }

    public ReviewNotFoundException(String message) {
        super(
                message,
                ErrorTypes.REVIEW_NOT_FOUND,
                ErrorCode.REVIEW_NOT_FOUND
        );
    }
}
