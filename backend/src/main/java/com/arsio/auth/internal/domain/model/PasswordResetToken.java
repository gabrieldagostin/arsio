package com.arsio.auth.internal.domain.model;

import com.arsio.auth.internal.domain.valueobject.PasswordResetTokenId;
import com.arsio.auth.internal.domain.valueobject.PasswordToken;
import com.arsio.shared.valueobject.UserId;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class PasswordResetToken {

    private final PasswordResetTokenId id;
    private final UserId userId;
    private final PasswordToken passwordToken;
    private boolean used;
    private final Instant expiresAt;

    public PasswordResetToken(PasswordResetTokenId id, UserId userId, PasswordToken passwordToken, boolean used, Instant expiresAt) {
        this.id = id;
        this.userId = userId;
        this.passwordToken = passwordToken;
        this.used = used;
        this.expiresAt = expiresAt;
    }

    public static PasswordResetToken create(UserId userId, PasswordToken passwordToken) {
        return new PasswordResetToken(
                new PasswordResetTokenId(UUID.randomUUID()),
                userId,
                passwordToken,
                false,
                Instant.now().plus(Duration.ofMinutes(15))
        );
    }

    public PasswordResetTokenId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public PasswordToken getPasswordToken() {
        return passwordToken;
    }

    public boolean isUsed() {
        return used;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
