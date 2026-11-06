package com.arsio.review.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidVoteTypeException extends ValidationException {

    public InvalidVoteTypeException() {
        super(
                "Invalid Vote Type, try again.",
                ErrorTypes.REVIEW_INVALID_VOTE_TYPE,
                ErrorCode.REVIEW_INVALID_VOTE_TYPE
        );
    }

    public InvalidVoteTypeException(String message) {
        super(
                message,
                ErrorTypes.REVIEW_INVALID_VOTE_TYPE,
                ErrorCode.REVIEW_INVALID_VOTE_TYPE
        );
    }
}
