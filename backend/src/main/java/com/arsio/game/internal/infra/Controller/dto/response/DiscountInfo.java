package com.arsio.game.internal.infra.Controller.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DiscountInfo(
        BigDecimal percentage,
        BigDecimal discountedAmount,
        LocalDate startDate,
        LocalDate endDate
) {
}
