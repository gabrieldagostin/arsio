package com.arsio.auth.internal.infra.controller.dto;

import jakarta.validation.constraints.*;

public record RegisterUserRequest(

        @Size(min = 6, max = 50)
        @NotBlank
        String username,

        @Email
        @Size(max = 320)
        @NotBlank
        String email,

        @Size(min = 8, max = 72)
        @NotBlank
        String password
) {
}
