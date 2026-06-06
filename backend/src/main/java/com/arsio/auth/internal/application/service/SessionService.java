package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.port.output.SessionRepository;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.domain.valueobject.UserId;
import com.arsio.auth.internal.infra.security.Sha256TokenHasher;
import com.arsio.auth.internal.infra.security.TokenProperties;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final TokenProperties tokenProperties;
    private final Sha256TokenHasher tokenHasher;

    public SessionService(SessionRepository sessionRepository, TokenProperties tokenProperties, Sha256TokenHasher tokenHasher) {
        this.sessionRepository = sessionRepository;
        this.tokenProperties = tokenProperties;
        this.tokenHasher = tokenHasher;
    }

    public void save(UserId userId, SessionId sessionId, RefreshToken refreshToken) {
        Instant expiresAt = Instant.now().plus(tokenProperties.getRefreshTokenExpiration());
        String refreshTokenHash = tokenHasher.hash(refreshToken.value());
        UserSession userSession = new UserSession(
                sessionId,
                userId,
                new RefreshToken(refreshTokenHash),
                expiresAt,
                false
        );

        sessionRepository.save(userSession);
    }

}
