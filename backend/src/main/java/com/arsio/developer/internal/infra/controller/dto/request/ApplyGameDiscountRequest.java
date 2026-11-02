package com.arsio.developer.internal.infra.controller.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ApplyGameDiscountRequest(

        @NotNull(message = "{percentage.require}")
        BigDecimal percentage,

        @NotNull(message = "{startDate.require}")
        @FutureOrPresent(message = "{startDate.futureOrPresent}")
        LocalDate startDate,

        @NotNull(message = "{endDate.require}")
        @Future(message = "{endDate.future}")
        LocalDate endDate
) {
}
