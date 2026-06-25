package com.arsio.user.api.dto;

import java.util.UUID;

public record UpdateUserPasswordHashCommand(
        UUID id,
        String passwordHash
) {
}
