package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.dto.ResetPasswordCommand;
import com.arsio.auth.internal.domain.exception.InvalidTokenException;
import com.arsio.auth.internal.domain.model.PasswordResetToken;
import com.arsio.auth.internal.domain.repository.PasswordResetTokenRepository;
import com.arsio.auth.internal.domain.repository.UserAuthRepository;
import com.arsio.user.api.facade.UserFacade;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResetPasswordService {

    private final PasswordResetTokenRepository passwordResetTokens;
    private final UserAuthRepository users;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;

    public ResetPasswordService(PasswordResetTokenRepository passwordResetTokens, UserAuthRepository users, PasswordEncoder passwordEncoder, UserFacade userFacade) {
        this.passwordResetTokens = passwordResetTokens;
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.userFacade = userFacade;
    }

    public void execute(ResetPasswordCommand command) {

        PasswordResetToken passwordResetToken = passwordResetTokens.findByToken(command.token());

        if (passwordResetToken.isExpired()) {
            throw new InvalidTokenException("Token Expirado");
        }
        if (passwordResetToken.isUsed()) {
            throw new InvalidTokenException("Token já foi utilizado");
        }

        String passwordHash = passwordEncoder.encode(command.newPassword());

        userFacade.updateUserPasswordHash(passwordResetToken.getUserId().value(), passwordHash);

        passwordResetToken.setUsed(true);

        passwordResetTokens.save(passwordResetToken);
    }
}
