package com.arsio.auth.internal.application.usecases;

import com.arsio.auth.internal.application.port.input.CreateUserUseCase;
import com.arsio.auth.internal.domain.exception.EmailAlreadyExistsException;
import com.arsio.auth.internal.domain.exception.UsernameUnavailableException;
import com.arsio.auth.internal.domain.model.User;
import com.arsio.auth.internal.domain.repository.UserRepository;
import com.arsio.auth.internal.domain.valueobject.Email;
import com.arsio.auth.internal.domain.valueobject.Username;
import com.arsio.auth.internal.infra.controller.dto.AuthenticatedUserResponse;
import com.arsio.auth.internal.infra.controller.dto.AuthenticationResponse;
import com.arsio.auth.internal.infra.controller.dto.CreateUserCommand;
import com.arsio.config.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CreateUserService implements CreateUserUseCase {

    private final UserRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;


    public CreateUserService(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public AuthenticationResponse execute(CreateUserCommand command) {

        boolean emailAlreadyExists = repository.existsByEmail(
                new Email(command.email())
        );

        boolean usernameAlreadyExists = repository.existsByUsernameNormalized(
                new Username(command.username())
        );

        if (emailAlreadyExists) throw new EmailAlreadyExistsException();

        if (usernameAlreadyExists) throw new UsernameUnavailableException();

        String passwordHash = passwordEncoder.encode(command.password());

        User user = User.createUser(
                command.username(),
                command.email(),
                passwordHash
        );

        repository.save(user);

        var token = tokenService.generateToken(user);

        return new AuthenticationResponse(
                token,
                new AuthenticatedUserResponse(
                        user.getId().value(),
                        user.getRole().name(),
                        user.getUsername().value(),
                        user.getProfileImageKey()));
    }
}
