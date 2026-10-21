package com.arsio.review.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidRatingException extends ValidationException {

    public InvalidRatingException() {
        super(
                "Invalid rating value, try again.",
                ErrorTypes.REVIEW_INVALID_RATING,
                ErrorCode.REVIEW_INVALID_RATING
        );
    }

    public InvalidRatingException(String message) {
        super(
                message,
                ErrorTypes.REVIEW_INVALID_RATING,
                ErrorCode.REVIEW_INVALID_RATING
        );
    }
}
