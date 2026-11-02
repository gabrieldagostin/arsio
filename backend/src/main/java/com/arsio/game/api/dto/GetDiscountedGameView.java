package com.arsio.game.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record GetDiscountedGameView(
        UUID gameId,
        String title,
        BigDecimal discountedPrice,
        BigDecimal discountedAmount,
        String thumbnailUrl
) {
}
