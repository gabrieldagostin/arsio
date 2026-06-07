package com.arsio.user.internal.application.service;

import com.arsio.user.api.dto.CreateUserCommand;
import com.arsio.user.internal.application.exception.EmailAlreadyExistsException;
import com.arsio.user.internal.application.exception.UsernameUnavailableException;
import com.arsio.user.internal.application.port.output.UserRepository;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(CreateUserCommand command) {

        boolean emailAlreadyExists = users.existsByEmail(
                new Email(command.email())
        );

        if (emailAlreadyExists) throw new EmailAlreadyExistsException();

        boolean usernameAlreadyExists = users.existsByUsernameNormalized(
                new Username(command.username())
        );

        if (usernameAlreadyExists) throw new UsernameUnavailableException();

        String passwordHash = passwordEncoder.encode(command.password());

        User user = User.createUser(
                command.username(),
                command.email(),
                passwordHash
        );

        users.save(user);

        return user;
    }
}
