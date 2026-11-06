package com.arsio.wishlist.internal.infra.controller.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record GetWishlistResponse(
        UUID id,
        String title,
        BigDecimal basePrice,
        String thumbnailUrl
) {
}
