package com.arsio.auth.internal.domain.valueobject;

import java.util.UUID;

public record SessionId(UUID value) {

    public static SessionId generate() {
        return new SessionId(UUID.randomUUID());
    }

}
