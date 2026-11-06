package com.arsio.developer.internal.infra.controller.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record GetDiscountedGameResponse(
        UUID gameId,
        String title,
        BigDecimal price,
        BigDecimal basePrice,
        String thumbnailUrl,
        DiscountInfo discountInfo
) {
}
