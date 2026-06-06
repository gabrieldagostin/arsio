package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.application.port.output.UserRepository;
import com.arsio.auth.internal.domain.valueobject.AccessToken;
import com.arsio.auth.internal.domain.valueobject.RefreshToken;
import com.arsio.auth.internal.domain.valueobject.SessionId;
import com.arsio.auth.internal.infra.controller.dto.AuthenticatedUserResponse;
import com.arsio.auth.internal.infra.controller.dto.AuthenticationResponse;
import com.arsio.auth.internal.application.dto.LoginUserComand;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenProvider tokenProvider;
    private final SessionService sessionService;

    public LoginUserService(AuthenticationManager authenticationManager, UserRepository repository, TokenProvider tokenProvider, SessionService sessionService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenProvider = tokenProvider;
        this.sessionService = sessionService;
    }

    public AuthenticationResponse execute(LoginUserComand command) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(command.username(), command.password());
        authenticationManager.authenticate(usernamePassword);

        User user = repository.findUserByUsernameNormalized(command.username());

        SessionId sessionId = SessionId.generate();

        AccessToken accesstoken = tokenProvider.generateAccessToken(user);
        RefreshToken refreshToken = tokenProvider.generateRefreshToken(user, sessionId);

        sessionService.save(user.getId(), sessionId, refreshToken);

        return new AuthenticationResponse(
                accesstoken.value(),
                refreshToken.value(),
                new AuthenticatedUserResponse(
                        user.getId().value(),
                        user.getRole().name(),
                        user.getUsername().value(),
                        user.getProfileImageKey())
        );
    }
}
