package com.arsio.auth.internal.infra.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ForgotPasswordRequest(

        @NotBlank(message = "{email.require}")
        @Email(message = "{email.format}")
        @Size(max = 320, message = "{email.size}")
        String email
) {
}
