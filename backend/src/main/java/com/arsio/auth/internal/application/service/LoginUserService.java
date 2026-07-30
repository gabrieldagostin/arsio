package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.domain.repository.UserAuthRepository;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.shared.exception.UserNotFoundException;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.controller.dto.AuthenticatedUserResponse;
import com.arsio.auth.internal.infra.controller.dto.AuthenticationResponse;
import com.arsio.auth.internal.application.dto.LoginUserComand;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginUserService {

    private final AuthenticationManager authenticationManager;
    private final UserAuthRepository users;
    private final TokenProvider tokenProvider;
    private final SessionService sessionService;

    public LoginUserService(AuthenticationManager authenticationManager, UserAuthRepository users, TokenProvider tokenProvider, SessionService sessionService) {
        this.authenticationManager = authenticationManager;
        this.users = users;
        this.tokenProvider = tokenProvider;
        this.sessionService = sessionService;
    }

    public AuthenticationResponse execute(LoginUserComand command) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(command.username(), command.password());
        authenticationManager.authenticate(usernamePassword);

        UserAuth userAuth = users.findAuthenticationDataByUsername(command.username())
                .orElseThrow(UserNotFoundException::new);

        SessionId sessionId = SessionId.generate();

        AccessToken accesstoken = tokenProvider.generateAccessToken(userAuth);
        RefreshToken refreshToken = tokenProvider.generateRefreshToken(userAuth, sessionId);

        sessionService.save(userAuth.getId(), sessionId, refreshToken);

        return new AuthenticationResponse(
                accesstoken.value(),
                refreshToken.value(),
                new AuthenticatedUserResponse(
                        userAuth.getId().value(),
                        userAuth.getRole(),
                        userAuth.getUsername(),
                        userAuth.getProfileImageKey())
        );
    }
}
