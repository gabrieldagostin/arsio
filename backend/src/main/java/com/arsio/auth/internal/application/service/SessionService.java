package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.domain.repository.SessionRepository;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.security.Sha256TokenHasher;
import com.arsio.auth.internal.infra.security.TokenProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessions;
    private final TokenProperties tokenProperties;
    private final Sha256TokenHasher tokenHasher;

    public SessionService(SessionRepository sessions, TokenProperties tokenProperties, Sha256TokenHasher tokenHasher) {
        this.sessions = sessions;
        this.tokenProperties = tokenProperties;
        this.tokenHasher = tokenHasher;
    }

    public void save(UUID userId, SessionId sessionId, RefreshToken refreshToken) {
        Instant expiresAt = Instant.now().plus(tokenProperties.getRefreshTokenExpiration());
        String refreshTokenHash = tokenHasher.hash(refreshToken.value());
        UserSession userSession = new UserSession(
                sessionId,
                userId,
                new RefreshToken(refreshTokenHash),
                expiresAt,
                false,
                null
        );

        sessions.save(userSession);
    }
}
