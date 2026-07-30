package com.arsio.notification.internal.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record NotificationId(UUID value) {

    public NotificationId {
        Objects.requireNonNull(value, "NotificationId cannot be null");
    }

    public NotificationId generate() {
        return new NotificationId(UUID.randomUUID());
    }
}
