package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.command.ResetPasswordCommand;
import com.arsio.auth.internal.domain.exception.PasswordResetTokenNotFoundException;
import com.arsio.auth.internal.domain.exception.SessionNotActiveException;
import com.arsio.auth.internal.domain.model.PasswordResetToken;
import com.arsio.auth.internal.domain.repository.PasswordResetTokenRepository;
import com.arsio.auth.internal.domain.repository.UserAuthRepository;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;
import com.arsio.user.api.facade.UserFacade;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResetPasswordService {

    private final PasswordResetTokenRepository passwordResetTokens;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    public ResetPasswordService(PasswordResetTokenRepository passwordResetTokens, UserAuthRepository users, PasswordEncoder passwordEncoder, UserFacade userFacade) {
        this.passwordResetTokens = passwordResetTokens;
        this.passwordEncoder = passwordEncoder;
        this.userFacade = userFacade;
    }

    public void execute(ResetPasswordCommand command) {

        PasswordResetToken passwordResetToken = passwordResetTokens.findByToken(command.passwordToken())
                .orElseThrow(PasswordResetTokenNotFoundException::new);

        if (passwordResetToken.isExpired())
            throw new SessionNotActiveException("Session is expired.");

        if (passwordResetToken.isUsed())
            throw new SessionNotActiveException("Session is revoked.");

        String passwordHash = passwordEncoder.encode(command.newPassword());

        userFacade.updateUserPasswordHash(passwordResetToken.getUserId(), passwordHash);

        passwordResetToken.setUsed(true);

        passwordResetTokens.save(passwordResetToken);
    }
}
