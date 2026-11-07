package com.arsio.wishlist.internal.infra.controller.dto.response;

import com.arsio.wishlist.internal.domain.dto.DiscountInfo;

import java.math.BigDecimal;
import java.util.UUID;

public record GetWishlistResponse(
        UUID id,
        String title,
        BigDecimal price,
        BigDecimal basePrice,
        String thumbnailUrl,
        DiscountInfo discountInfo
) {
}
