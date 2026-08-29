package com.arsio.notification.internal.application.service;

import com.arsio.notification.internal.application.command.SendEmailCommand;
import com.arsio.notification.internal.application.port.output.EmailSender;
import com.arsio.notification.internal.domain.model.EmailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailService {

    private final EmailSender emailSender;

    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(SendEmailCommand command) {

        EmailMessage emailMessage = new EmailMessage(command.to(), command.subject().value(), command.body().value());

        emailSender.sendEmail(emailMessage);
    }
}
