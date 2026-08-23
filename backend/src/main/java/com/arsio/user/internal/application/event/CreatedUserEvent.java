package com.arsio.user.internal.application.event;

import java.util.UUID;

public record CreatedUserEvent(
        UUID userId
) {
}
