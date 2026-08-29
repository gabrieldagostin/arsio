package com.arsio.notification.internal.infra.email;

import com.arsio.notification.internal.application.port.output.EmailSender;
import com.arsio.notification.internal.domain.model.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JavaMailEmailSender implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailMessage emailMessage) {

        var message = new SimpleMailMessage();

        message.setFrom("noreply@gmail.com");
        message.setTo(emailMessage.to());
        message.setSubject(emailMessage.subject().value());
        message.setText(emailMessage.body().value());

        mailSender.send(message);
    }
}
