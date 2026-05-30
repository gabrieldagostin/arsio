package com.arsio.auth.internal.domain.valueobject;

import java.util.UUID;

public record UserId(UUID value) {

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public String getValue() {
        return value.toString();
    }
}
