package com.arsio.review.internal.domain.dto;

import java.util.UUID;

public record AuthorSummary(
        UUID id,
        String username,
        String avatarUrl
) {
}
