package com.arsio.game.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record GameSummary(
        UUID id,
        String title,
        BigDecimal price,
        String thumbnailUrl
) {
}
