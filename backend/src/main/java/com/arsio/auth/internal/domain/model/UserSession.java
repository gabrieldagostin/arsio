package com.arsio.auth.internal.domain.model;

import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.shared.valueobject.UserId;

import java.time.Instant;

public class UserSession {

    private final SessionId id;
    private final UserId userId;
    private RefreshToken refreshTokenHash;
    private final Instant expiresAt;
    private boolean revoked;

    public UserSession(SessionId id, UserId userId, RefreshToken refreshTokenHash, Instant expiresAt, boolean revoked) {
        this.id = id;
        this.userId = userId;
        this.refreshTokenHash = refreshTokenHash;
        this.expiresAt = expiresAt;
        this.revoked = revoked;
    }

    public SessionId getSessionId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public RefreshToken getRefreshTokenHash() {
        return refreshTokenHash;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public boolean isActive() {
        return !isRevoked() && !isExpired();
    }

    public void updateRefreshTokenHash(String newHash) {
        this.refreshTokenHash = new RefreshToken(newHash);
    }

    public void logout() {
        this.revoked = true;
    }
}
