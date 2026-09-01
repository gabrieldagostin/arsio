package com.arsio.notification.internal.infra.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SendEmailRequest(

        @NotBlank(message = "{to.required}")
        @Email(message = "{email.format}")
        @Size(max = 320, message = "{email.size}")
        String to,

        @NotBlank(message = "{subject.required}")
        String subject,

        @NotBlank(message = "{body.required}")
        String body
) {
}
