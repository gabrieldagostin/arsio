package com.arsio.auth.internal.domain.valueobject;

import java.util.UUID;

public record PasswordResetTokenId(UUID value) {

    public static PasswordResetTokenId generate() {
        return new PasswordResetTokenId(UUID.randomUUID());
    }
}
