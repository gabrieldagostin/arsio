package com.arsio.notification.internal.domain.valueobject;

import java.util.UUID;

public record NotificationId(UUID value) {

    public NotificationId generate() {
        return new NotificationId(UUID.randomUUID());
    }
}
