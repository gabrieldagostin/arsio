package com.arsio.notification.internal.domain.model;

import com.arsio.notification.internal.domain.valueobject.Message;
import com.arsio.notification.internal.domain.valueobject.Title;

public record EmailMessage(
        String to,
        Title subject,
        Message body
) {
}
