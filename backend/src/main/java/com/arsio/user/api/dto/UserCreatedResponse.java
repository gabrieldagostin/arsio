package com.arsio.user.api.dto;

import java.util.UUID;

public record UserCreatedResponse(
        UUID id,
        String username,
        String email,
        String passwordHash,
        String role
) {
}
