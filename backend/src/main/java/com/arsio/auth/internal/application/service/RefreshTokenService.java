package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.dto.RefreshTokenCommand;
import com.arsio.auth.internal.application.exception.InvalidSessionException;
import com.arsio.auth.internal.application.exception.SessionNotFoundException;
import com.arsio.auth.internal.application.exception.UserNotFoundException;
import com.arsio.auth.internal.application.port.output.SessionRepository;
import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.application.port.output.UserRepository;
import com.arsio.auth.internal.domain.exception.InvalidTokenException;
import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.controller.dto.RefreshTokenResponse;
import com.arsio.auth.internal.infra.security.Sha256TokenHasher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final SessionRepository sessionRepository;
    private final Sha256TokenHasher tokenHasher;

    public RefreshTokenService(UserRepository userRepository, TokenProvider tokenProvider, SessionRepository sessionRepository, Sha256TokenHasher tokenHasher) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.sessionRepository = sessionRepository;
        this.tokenHasher = tokenHasher;
    }

    public RefreshTokenResponse execute(RefreshTokenCommand command) {
        SessionId sessionId = tokenProvider.extractSessionId(command.refreshToken());
        UserSession userSession = sessionRepository.findBySessionId(sessionId.value());

        if (userSession == null) throw new SessionNotFoundException("Sessão não encontrada");

        String subject = tokenProvider.extractSubject(command.refreshToken());
        User user = userRepository.findUserByUsernameNormalized(subject);

        if (user == null) throw new UserNotFoundException("Usuário não encontrado");

        String receivedHash = tokenHasher.hash(command.refreshToken());
        if (!receivedHash.equals(userSession.getRefreshTokenHash().value()))
        throw new InvalidTokenException("Invalid Refresh Token");

        if (!userSession.isActive()) throw new InvalidSessionException("Sessão inválida");

        AccessToken newAccessToken = tokenProvider.generateAccessToken(user);
        RefreshToken newRefreshToken = tokenProvider.generateRefreshToken(user, sessionId);

        String newHash = tokenHasher.hash(newRefreshToken.value());

        userSession.updateRefreshTokenHash(newHash);

        sessionRepository.save(userSession);

        return new RefreshTokenResponse(
                newAccessToken.value(),
                newRefreshToken.value()
        );
    }
}
