package com.arsio.developer.internal.infra.controller.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DiscountInfo(
        BigDecimal percentage,
        BigDecimal discountedAmount,
        LocalDate startDate,
        LocalDate endDate
) {
}
