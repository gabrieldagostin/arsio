package com.arsio.notification.internal.application.port.output;

import com.arsio.notification.internal.domain.model.EmailMessage;

public interface EmailSender {

    void sendEmail(EmailMessage emailMessage);
}
