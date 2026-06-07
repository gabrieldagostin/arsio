package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.application.port.output.UserAuthRepository;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.user.api.facade.UserFacade;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.controller.dto.AuthenticatedUserResponse;
import com.arsio.auth.internal.infra.controller.dto.AuthenticationResponse;
import com.arsio.auth.internal.application.dto.LoginUserComand;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoginUserService {

    private final AuthenticationManager authenticationManager;
    private final UserAuthRepository userAuthRepository;
    private final TokenProvider tokenProvider;
    private final SessionService sessionService;
    private final UserFacade userFacade;

    public LoginUserService(AuthenticationManager authenticationManager, UserAuthRepository userAuthRepository, TokenProvider tokenProvider, SessionService sessionService, UserFacade userFacade) {
        this.authenticationManager = authenticationManager;
        this.userAuthRepository = userAuthRepository;
        this.tokenProvider = tokenProvider;
        this.sessionService = sessionService;
        this.userFacade = userFacade;
    }

    public AuthenticationResponse execute(LoginUserComand command) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(command.username(), command.password());
        authenticationManager.authenticate(usernamePassword);

        UUID userId = userFacade.findUserByUsernameNormalized(command.username());

        Optional<UserAuth> userAuth = userAuthRepository.findUserById(userId);

        SessionId sessionId = SessionId.generate();

        AccessToken accesstoken = tokenProvider.generateAccessToken(userAuth);
        RefreshToken refreshToken = tokenProvider.generateRefreshToken(userAuth, sessionId);

        sessionService.save(userAuth.get().getId(), sessionId, refreshToken);

        return new AuthenticationResponse(
                accesstoken.value(),
                refreshToken.value(),
                new AuthenticatedUserResponse(
                        userAuth.get().getId(),
                        userAuth.get().getUserRole(),
                        userAuth.get().getUsername(),
                        userAuth.get().getProfileImageKey())
        );
    }
}
