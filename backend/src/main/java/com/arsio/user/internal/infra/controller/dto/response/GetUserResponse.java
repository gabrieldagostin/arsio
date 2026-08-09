package com.arsio.user.internal.infra.controller.dto.response;

import java.util.UUID;

public record GetUserResponse(
        UUID id,
        String username,
        String email,
        String role
) {
}
