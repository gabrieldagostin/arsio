package com.arsio.user.api.dto;

import java.util.UUID;

public record GetUserSummaryView(
        UUID id,
        String username,
        String avatarUrl
) {
}
