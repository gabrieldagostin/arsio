package com.arsio.cart.internal.infra.controller.dto.response;

import com.arsio.cart.internal.domain.dto.DiscountInfo;

import java.math.BigDecimal;
import java.util.UUID;

public record CartResponse(
        UUID id,
        String title,
        BigDecimal price,
        BigDecimal basePrice,
        String thumbnailUrl,
        DiscountInfo discountInfo
) {
}
