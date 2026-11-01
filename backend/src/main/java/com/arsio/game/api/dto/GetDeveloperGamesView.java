package com.arsio.game.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record GetDeveloperGamesView(
        UUID gameId,
        String title,
        BigDecimal basePrice,
        String thumbnailUrl
) {
}
