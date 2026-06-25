package com.arsio.notification.internal.application.dto;

public record SendEmailCommand(
        String to,
        String subject,
        String body
) {
}
