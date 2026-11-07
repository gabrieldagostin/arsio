package com.arsio.cart.internal.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DiscountInfo(
        BigDecimal percentage,
        BigDecimal discountedAmount,
        LocalDate startDate,
        LocalDate endDate
) {
}
