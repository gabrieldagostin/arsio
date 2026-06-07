package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.mapper.UserAuthMapper;
import com.arsio.auth.internal.domain.model.UserAuth;
import com.arsio.user.api.dto.UserCreatedResponse;
import com.arsio.user.api.facade.UserFacade;
import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.domain.valueobject.*;
import com.arsio.auth.internal.infra.controller.dto.AuthenticatedUserResponse;
import com.arsio.auth.internal.infra.controller.dto.AuthenticationResponse;
import com.arsio.auth.internal.application.dto.CreateUserCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateSessionService {

    private final TokenProvider tokenProvider;
    private final SessionService sessionService;
    private final UserFacade userFacade;
    private final UserAuthMapper userAuthMapper;

    public CreateSessionService(TokenProvider tokenProvider, SessionService sessionService, UserFacade userFacade, UserAuthMapper userAuthMapper) {
        this.tokenProvider = tokenProvider;
        this.sessionService = sessionService;
        this.userFacade = userFacade;
        this.userAuthMapper = userAuthMapper;
    }

    public AuthenticationResponse execute(CreateUserCommand command) {

        UserCreatedResponse userResponse = userFacade.createUser(
                command.username(),
                command.email(),
                command.password()
        );

        UserAuth userAuth = userAuthMapper.toDomain(userResponse);

        SessionId sessionId = SessionId.generate();

        AccessToken accesstoken = tokenProvider.generateAccessToken(Optional.ofNullable(userAuth));
        RefreshToken refreshToken = tokenProvider.generateRefreshToken(Optional.ofNullable(userAuth), sessionId);

        sessionService.save(userAuth.getId(), sessionId, refreshToken);

        return new AuthenticationResponse(
                accesstoken.value(),
                refreshToken.value(),
                new AuthenticatedUserResponse(
                        userAuth.getId(),
                        userAuth.getUserRole(),
                        userAuth.getUsername(),
                        null
                )
        );
    }
}
