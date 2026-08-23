package com.arsio.user.internal.infra.controller.dto.response;

import java.util.UUID;

public record FriendResponse(
        UUID userId,
        String username,
        String avatarUrl
) {
}
