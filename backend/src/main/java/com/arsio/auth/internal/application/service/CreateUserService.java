package com.arsio.auth.internal.application.service;

import com.arsio.auth.internal.application.exception.EmailAlreadyExistsException;
import com.arsio.auth.internal.application.exception.UsernameUnavailableException;
import com.arsio.auth.internal.application.port.output.TokenProvider;
import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.application.port.output.UserRepository;
import com.arsio.auth.internal.domain.valueobject.*;
import com.arsio.auth.internal.infra.controller.dto.AuthenticatedUserResponse;
import com.arsio.auth.internal.infra.controller.dto.AuthenticationResponse;
import com.arsio.auth.internal.application.dto.CreateUserCommand;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CreateUserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;

    public CreateUserService(UserRepository userRepository, TokenProvider tokenProvider, PasswordEncoder passwordEncoder, SessionService sessionService) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.sessionService = sessionService;
    }

    public AuthenticationResponse execute(CreateUserCommand command) {

        long start = System.currentTimeMillis();

        boolean emailAlreadyExists = userRepository.existsByEmail(
                new Email(command.email())
        );

        System.out.println("existsByEmail: " +
                (System.currentTimeMillis() - start) + "ms");

        boolean usernameAlreadyExists = userRepository.existsByUsernameNormalized(
                new Username(command.username())
        );

        System.out.println("existsByUsername: " +
                (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();

        if (emailAlreadyExists) throw new EmailAlreadyExistsException();

        if (usernameAlreadyExists) throw new UsernameUnavailableException();

        String passwordHash = passwordEncoder.encode(command.password());

        System.out.println("passwordEncoder: " +
                (System.currentTimeMillis() - start) + "ms");

        User user = User.createUser(
                command.username(),
                command.email(),
                passwordHash
        );

        userRepository.save(user);

        SessionId sessionId = SessionId.generate();

        AccessToken accesstoken = tokenProvider.generateAccessToken(user);
        RefreshToken refreshToken = tokenProvider.generateRefreshToken(user, sessionId);

        start = System.currentTimeMillis();

        sessionService.save(user.getId(), sessionId, refreshToken);

        System.out.println(
                "saveSession: " +
                        (System.currentTimeMillis() - start) +
                        "ms"
        );

        return new AuthenticationResponse(
                accesstoken.value(),
                refreshToken.value(),
                new AuthenticatedUserResponse(
                        user.getId().value(),
                        user.getRole().name(),
                        user.getUsername().value(),
                        user.getProfileImageKey()));
    }
}
