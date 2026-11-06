package com.arsio.review.internal.infra.controller.dto.response;

import java.util.UUID;

public record AuthorSummary(
        UUID id,
        String username,
        String avatarUrl
) {
}
