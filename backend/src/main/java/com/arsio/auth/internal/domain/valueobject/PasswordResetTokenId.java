package com.arsio.auth.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record PasswordResetTokenId(UUID value) {

    public PasswordResetTokenId {
        Objects.requireNonNull(value, "PasswordResetTokenId cannot be null.");
    }

    public static PasswordResetTokenId generate() {
        return new PasswordResetTokenId(UUID.randomUUID());
    }
}
