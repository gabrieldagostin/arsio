package com.arsio.notification.internal.application.listener;

import com.arsio.auth.internal.application.event.PasswordResetEvent;
import com.arsio.notification.internal.application.port.output.EmailSender;
import com.arsio.notification.internal.domain.model.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordResetNotificationListener {

    private final EmailSender emailSender;

    @Async
    @ApplicationModuleListener
    public void on(PasswordResetEvent event) {
        EmailMessage message = new EmailMessage(
                event.email(),
                "Recuperação de senha",
                """
                Você solicitou redefinição de senha.

                Acesse:
                %s
                """.formatted(event.resetLink())
                );

        emailSender.sendEmail(message);
    }
}
