package com.arsio.auth.internal.infra.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(

        @NotBlank(message = "{resetToken.require}")
        String token,

        @NotBlank(message = "{password.require}")
        String newPassword
) {
}
