package com.arsio.developer.internal.infra.controller.dto.response;

import java.math.BigDecimal;

public record UpdateGameDiscountResponse(
        BigDecimal price,
        BigDecimal basePrice,
        DiscountInfo discountInfo
) {
}
