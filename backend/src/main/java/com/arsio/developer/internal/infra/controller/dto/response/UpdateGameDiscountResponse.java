package com.arsio.developer.internal.infra.controller.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateGameDiscountResponse(
        BigDecimal discountedPrice,
        BigDecimal discountedAmount,
        LocalDate startDate,
        LocalDate endDate
) {
}
