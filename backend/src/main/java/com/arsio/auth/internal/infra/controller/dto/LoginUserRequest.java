package com.arsio.auth.internal.infra.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(

        @NotBlank(message = "{username.require}")
        String username,

        @NotBlank(message = "{password.require}")
        String password
) {
}
