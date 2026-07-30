package com.arsio.auth.internal.domain.repository;

import com.arsio.auth.internal.domain.model.PasswordResetToken;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;

import java.util.Optional;

public interface PasswordResetTokenRepository {

    void save(PasswordResetToken passwordResetToken);

    Optional<PasswordResetToken> findByToken(PasswordToken token);
}
