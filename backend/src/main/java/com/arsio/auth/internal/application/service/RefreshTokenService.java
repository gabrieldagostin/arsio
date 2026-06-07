package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.dto.RefreshTokenCommand;
import com.arsio.auth.internal.application.exception.InvalidSessionException;
import com.arsio.auth.internal.application.exception.SessionNotFoundException;
import com.arsio.auth.internal.application.port.output.UserAuthRepository;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.shared.exception.UserNotFoundException;
import com.arsio.auth.internal.application.port.output.SessionRepository;
import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.user.api.facade.UserFacade;
import com.arsio.auth.internal.domain.exception.InvalidTokenException;
import com.arsio.auth.internal.domain.model.UserSession;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.controller.dto.RefreshTokenResponse;
import com.arsio.auth.internal.infra.security.Sha256TokenHasher;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final UserAuthRepository userAuthRepository;
    private final TokenProvider tokenProvider;
    private final SessionRepository sessionRepository;
    private final Sha256TokenHasher tokenHasher;
    private final UserFacade userFacade;

    public RefreshTokenService(UserAuthRepository userAuthRepository, TokenProvider tokenProvider, SessionRepository sessionRepository, Sha256TokenHasher tokenHasher, UserFacade userFacade) {
        this.userAuthRepository = userAuthRepository;
        this.tokenProvider = tokenProvider;
        this.sessionRepository = sessionRepository;
        this.tokenHasher = tokenHasher;
        this.userFacade = userFacade;
    }

    public RefreshTokenResponse execute(RefreshTokenCommand command) {
        SessionId sessionId = tokenProvider.extractSessionId(command.refreshToken());
        UserSession userSession = sessionRepository.findBySessionId(sessionId.value());

        if (userSession == null) throw new SessionNotFoundException("Sessão não encontrada");

        String subject = tokenProvider.extractSubject(command.refreshToken());
        UUID userId = userFacade.findUserByUsernameNormalized(subject);
        Optional<UserAuth> userAuth = userAuthRepository.findUserById(userId);

        if (userAuth == null) throw new UserNotFoundException("Usuário não encontrado");

        String receivedHash = tokenHasher.hash(command.refreshToken());
        if (!receivedHash.equals(userSession.getRefreshTokenHash().value()))
        throw new InvalidTokenException("Invalid Refresh Token");

        if (!userSession.isActive()) throw new InvalidSessionException("Sessão inválida");

        AccessToken newAccessToken = tokenProvider.generateAccessToken(userAuth);
        RefreshToken newRefreshToken = tokenProvider.generateRefreshToken(userAuth, sessionId);

        String newHash = tokenHasher.hash(newRefreshToken.value());

        userSession.updateRefreshTokenHash(newHash);

        sessionRepository.save(userSession);

        return new RefreshTokenResponse(
                newAccessToken.value(),
                newRefreshToken.value()
        );
    }
}
