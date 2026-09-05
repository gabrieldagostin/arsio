package com.arsio.user.internal.infra.controller.dto.response;

import java.util.UUID;

public record ListUserResponse(
        UUID id,
        String displayName,
        String avatarUrl
) {
}
