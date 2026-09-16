package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateGameRequest(

        @NotBlank(message = "{title.require}")
        @Size(max = 150, message = "{title.size}")
        String title,

        @NotBlank(message = "{description.require}")
        @Size(max = 1000, message = "{description.size}")
        String description,

        @NotNull(message = "{basePrice.require}")
        @DecimalMin(value = "0.00", message = "{basePrice.min}")
        @Digits(integer = 8, fraction = 2, message = "{basePrice.digits}")
        BigDecimal basePrice,

        @NotNull(message = "{releaseDate.require}")
        @FutureOrPresent(message = "{releaseDate.futureOrPresent}")
        LocalDate releaseDate
) {
}
