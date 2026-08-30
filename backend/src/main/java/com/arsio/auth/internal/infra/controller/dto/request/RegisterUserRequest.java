package com.arsio.auth.internal.infra.controller.dto.request;

import jakarta.validation.constraints.*;

public record RegisterUserRequest(

        @NotBlank(message = "{username.required}")
        @Size(min = 6, max = 50, message = "{username.size}")
        String username,

        @NotBlank(message = "{email.require}")
        @Email(message = "{email.format}")
        @Size(max = 320, message = "{email.size}")
        String email,

        @NotBlank(message = "{password.require}")
        @Size(min = 8, max = 72, message = "{password.size}")
        String password
) {
}
