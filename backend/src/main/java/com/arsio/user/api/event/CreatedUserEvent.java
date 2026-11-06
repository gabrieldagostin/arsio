package com.arsio.user.api.event;

import java.util.UUID;

public record CreatedUserEvent(
        UUID userId
) {
}
