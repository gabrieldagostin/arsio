package com.arsio.notification.internal.infra.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record SendEmailRequest(

        @NotBlank(message = "{to.required}")
        String to,

        @NotBlank(message = "{subject.required}")
        String subject,

        @NotBlank(message = "{body.required}")
        String body
) {
}
