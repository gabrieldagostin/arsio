package com.arsio.game.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DiscountId(UUID value) {

    public DiscountId {
        Objects.requireNonNull(value, "DiscountId cannot be null.");
    }

    public DiscountId generate() {
        return new DiscountId(UUID.randomUUID());
    }
}
