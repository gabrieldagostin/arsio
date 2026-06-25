package com.arsio.notification.internal.infra.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record SendEmailRequest(

        @NotBlank
        String to,

        @NotBlank
        String subject,

        @NotBlank
        String body
) {
}
