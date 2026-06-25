package com.arsio.notification.internal.domain.model;

public record EmailMessage(
        String to,
        String subject,
        String body
) {
}
