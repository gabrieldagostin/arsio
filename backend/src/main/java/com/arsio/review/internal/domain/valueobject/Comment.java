package com.arsio.review.internal.domain.valueobject;

import com.arsio.review.internal.domain.exception.InvalidCommentLengthException;

public record Comment(String value) {

    public Comment {
        value = value == null ? null : value.trim();
        validate(value);
    }

    private static void validate(String value) {

        if (value != null && value.length() > 300)
            throw new InvalidCommentLengthException();
    }
}
