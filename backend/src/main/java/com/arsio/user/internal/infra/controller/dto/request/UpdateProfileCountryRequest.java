package com.arsio.user.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProfileCountryRequest(

        @NotBlank(message = "{profileCountry.required}")
        @Size(min = 2, max = 2, message = "{profileCountry.required}")
        String newCountry
) {
}
