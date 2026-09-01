package com.arsio.auth.internal.domain.model;

import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.user.internal.domain.valueobject.UserId;

import java.time.Instant;
import java.util.UUID;

public class UserSession {

    private final SessionId id;
    private final UUID userId;
    private RefreshToken refreshTokenHash;
    private final Instant expiresAt;
    private boolean revoked;
    private Instant revokedAt;

    public UserSession(SessionId id, UUID userId, RefreshToken refreshTokenHash, Instant expiresAt, boolean revoked, Instant revokedAt) {
        this.id = id;
        this.userId = userId;
        this.refreshTokenHash = refreshTokenHash;
        this.expiresAt = expiresAt;
        this.revoked = revoked;
        this.revokedAt = revokedAt;
    }

    public SessionId getId() {
        return id;
    }

    public UUID getUserId() {
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

    public Instant getRevokedAt() {
        return revokedAt;
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
        this.revokedAt = Instant.now();
    }
}
