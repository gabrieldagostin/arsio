package com.arsio.game.internal.infra.Controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateGpuRequest(

        @NotBlank(message = "{manufacturer.require}")
        @Size(max = 50, message = "{manufacturer.size}")
        String manufacturer,

        @NotBlank(message = "{model.require}")
        @Size(max = 150, message = "{model.size}")
        String model
) {
}
