package com.arsio.game.internal.infra.Controller.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ListGamesResponse(
        UUID id,
        String title,
        BigDecimal basePrice,
        String thumbnailUrl
) {
}
