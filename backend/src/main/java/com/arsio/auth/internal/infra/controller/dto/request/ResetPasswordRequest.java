package com.arsio.auth.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordRequest(

        @NotBlank(message = "{resetToken.require}")
        String passwordToken,

        @NotBlank(message = "{password.require}")
        @Size(min = 8, max = 72, message = "{password.size}")
        String newPassword
) {
}
