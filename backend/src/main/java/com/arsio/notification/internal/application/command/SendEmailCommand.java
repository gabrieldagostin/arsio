package com.arsio.notification.internal.application.command;

import com.arsio.notification.internal.domain.valueobject.Message;
import com.arsio.notification.internal.domain.valueobject.Title;

public record SendEmailCommand(
        String to,
        Title subject,
        Message body
) {
}
