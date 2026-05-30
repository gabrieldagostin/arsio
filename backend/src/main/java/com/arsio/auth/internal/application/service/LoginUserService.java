package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.port.input.LoginUserUseCase;
import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.application.port.output.UserRepository;
import com.arsio.auth.internal.infra.controller.dto.AuthenticatedUserResponse;
import com.arsio.auth.internal.infra.controller.dto.AuthenticationResponse;
import com.arsio.auth.internal.application.dto.LoginUserComand;
import com.arsio.config.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements LoginUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository repository;

    public LoginUserService(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository repository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    public AuthenticationResponse execute(LoginUserComand command) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(command.username(), command.password());
        authenticationManager.authenticate(usernamePassword);

        User user = repository.findUserByUsernameNormalized(command.username());

        var token = tokenService.generateToken(user);

        return new AuthenticationResponse(
                token,
                new AuthenticatedUserResponse(
                        user.getId().value(),
                        user.getRole().name(),
                        user.getUsername().value(),
                        user.getProfileImageKey())
        );
    }
}
