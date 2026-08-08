package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.dto.RefreshTokenCommand;
import com.arsio.auth.internal.domain.exception.SessionNotActiveException;
import com.arsio.auth.internal.domain.exception.SessionNotFoundException;
import com.arsio.auth.internal.domain.repository.UserAuthRepository;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.auth.internal.domain.repository.SessionRepository;
import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.domain.exception.InvalidTokenException;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.controller.dto.response.RefreshTokenResponse;
import com.arsio.auth.internal.infra.security.Sha256TokenHasher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RefreshTokenService {

    private final UserAuthRepository users;
    private final TokenProvider tokenProvider;
    private final SessionRepository sessions;
    private final Sha256TokenHasher tokenHasher;

    public RefreshTokenService(UserAuthRepository users, TokenProvider tokenProvider, SessionRepository sessions, Sha256TokenHasher tokenHasher) {
        this.users = users;
        this.tokenProvider = tokenProvider;
        this.sessions = sessions;
        this.tokenHasher = tokenHasher;
    }

    public RefreshTokenResponse execute(RefreshTokenCommand command) {
        SessionId sessionId = tokenProvider.extractSessionId(command.refreshToken());
        UserSession userSession = sessions.findBySessionId(sessionId)
                .orElseThrow(SessionNotFoundException::new);

        String subject = tokenProvider.extractSubject(command.refreshToken());
        UserAuth userAuth = users.findAuthenticationDataByUsername(subject)
                .orElseThrow(InvalidTokenException::new);

        String receivedHash = tokenHasher.hash(command.refreshToken());
        if (!receivedHash.equals(userSession.getRefreshTokenHash().value()))
            throw new InvalidTokenException();

        if (!userSession.isActive()) throw new SessionNotActiveException();

        AccessToken newAccessToken = tokenProvider.generateAccessToken(userAuth);
        RefreshToken newRefreshToken = tokenProvider.generateRefreshToken(userAuth, sessionId);

        String newHash = tokenHasher.hash(newRefreshToken.value());

        userSession.updateRefreshTokenHash(newHash);

        sessions.save(userSession);

        return new RefreshTokenResponse(
                newAccessToken.value(),
                newRefreshToken.value()
        );
    }
}
