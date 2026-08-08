package com.arsio.auth.internal.infra.controller.dto.response;

import java.util.UUID;

public record AuthenticatedUserResponse(
        UUID id,
        String role,
        String username,
        String avatarUrl
) {

    public AuthenticatedUserResponse(UUID id, String role, String username, String avatarUrl) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.avatarUrl = avatarUrl;
    }
}
