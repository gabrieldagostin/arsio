package com.arsio.auth.internal.domain.repository;

import com.arsio.auth.internal.domain.model.PasswordResetToken;

public interface PasswordResetTokenRepository {

    void save(PasswordResetToken passwordResetToken);

    PasswordResetToken findByToken(String token);
}
