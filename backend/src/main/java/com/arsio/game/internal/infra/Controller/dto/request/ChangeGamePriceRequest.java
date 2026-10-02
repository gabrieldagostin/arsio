package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ChangeGamePriceRequest(

        @NotNull(message = "{basePrice.require}")
        @DecimalMin(value = "0.00", message = "{basePrice.min}")
        @Digits(integer = 8, fraction = 2, message = "{basePrice.digits}")
        BigDecimal newPrice
) {
}
