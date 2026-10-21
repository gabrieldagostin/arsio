package com.arsio.review.internal.domain.valueobject;

import com.arsio.review.internal.domain.exception.InvalidRatingException;

public record Rating(Integer value) {

    public Rating {
        validate(value);
    }

    private void validate(Integer value) {

        if (value == null || value < 1 || value > 5) {
            throw new InvalidRatingException();
        }
    }
}
