package com.arsio.review.internal.domain.exception;

import com.arsio.shared.exception.ValidationException;
import com.arsio.shared.exception.constants.ErrorTypes;
import com.arsio.shared.exception.enums.ErrorCode;

public class InvalidCommentLengthException extends ValidationException {

    public InvalidCommentLengthException() {
        super(
                "Invalid Vote Type, try again.",
                ErrorTypes.REVIEW_INVALID_COMMENT_LENGTH,
                ErrorCode.REVIEW_INVALID_COMMENT_LENGTH
        );
    }

    public InvalidCommentLengthException(String message) {
        super(
                message,
                ErrorTypes.REVIEW_INVALID_COMMENT_LENGTH,
                ErrorCode.REVIEW_INVALID_COMMENT_LENGTH
        );
    }
}
