package com.arsio.auth.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginUserRequest(

        @NotBlank(message = "{username.require}")
        @Size(min = 6, max = 50, message = "{username.size}")
        String username,

        @NotBlank(message = "{password.require}")
        @Size(min = 8, max = 72, message = "{password.size}")
        String password
) {
}
