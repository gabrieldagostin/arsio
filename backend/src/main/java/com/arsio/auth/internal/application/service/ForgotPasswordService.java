package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.dto.ForgotPasswordCommand;
import com.arsio.auth.internal.application.event.PasswordResetEvent;
import com.arsio.auth.internal.domain.repository.PasswordResetTokenRepository;
import com.arsio.auth.internal.domain.repository.UserAuthRepository;
import com.arsio.auth.internal.domain.model.PasswordResetToken;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;
import com.arsio.shared.config.FrontendProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ForgotPasswordService {

    private final UserAuthRepository users;
    private final PasswordResetTokenRepository passwordResetTokens;
    private final ApplicationEventPublisher eventPublisher;
    private final FrontendProperties frontend;

    public ForgotPasswordService(UserAuthRepository users, PasswordResetTokenRepository passwordResetTokens, ApplicationEventPublisher eventPublisher, FrontendProperties frontend) {
        this.users = users;
        this.passwordResetTokens = passwordResetTokens;
        this.eventPublisher = eventPublisher;
        this.frontend = frontend;
    }

    public void execute(ForgotPasswordCommand command) {

        UserAuth userAuth = users.findUserByEmail(command.email());

        PasswordToken passwordToken = new PasswordToken(UUID.randomUUID().toString());

        PasswordResetToken passwordResetToken = PasswordResetToken.create(userAuth.getId(), passwordToken);

        passwordResetTokens.save(passwordResetToken);

        String resetLink = frontend.getUrl() +
                "/reset-password?token=" +
                passwordResetToken.getPasswordToken().value();

        eventPublisher.publishEvent(
                new PasswordResetEvent(
                        userAuth.getEmail(),
                        resetLink
                )
        );

    }
}
