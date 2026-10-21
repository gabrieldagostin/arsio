package com.arsio.review.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ReviewId(UUID value) {

    public ReviewId {
        Objects.requireNonNull(value, "ReviewId cannot be null.");
    }

    public static ReviewId generate() {
        return new ReviewId(UUID.randomUUID());
    }
}
