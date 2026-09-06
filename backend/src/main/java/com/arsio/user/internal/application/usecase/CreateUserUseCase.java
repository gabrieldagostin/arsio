package com.arsio.user.internal.application.usecase;

import com.arsio.user.api.dto.CreateUserCommand;
import com.arsio.user.internal.application.event.CreatedUserEvent;
import com.arsio.user.internal.domain.exception.EmailAlreadyExistsException;
import com.arsio.user.internal.domain.exception.UsernameUnavailableException;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.valueobject.Email;
import com.arsio.user.internal.domain.valueobject.Username;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Transactional
public class CreateUserUseCase {

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;
    private final ProfileRepository profiles;

    public CreateUserUseCase(UserRepository users, PasswordEncoder passwordEncoder, ApplicationEventPublisher eventPublisher, ProfileRepository profiles) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
        this.profiles = profiles;
    }

    public User execute(CreateUserCommand command) {

        boolean emailAlreadyExists = users.existsByEmail(
                new Email(command.email())
        );

        if (emailAlreadyExists) throw new EmailAlreadyExistsException();

        boolean usernameAlreadyExists = users.existsByUsername(
                new Username(command.username())
        );

        if (usernameAlreadyExists) throw new UsernameUnavailableException(command.username());

        String passwordHash = passwordEncoder.encode(command.password());

        User user = User.create(
                command.username().toLowerCase(Locale.ROOT),
                command.email(),
                passwordHash
        );
        users.save(user);

        Profile profile = Profile.create(
                user.getId().value(),
                command.username()
        );
        profiles.save(profile);

        eventPublisher.publishEvent(
                new CreatedUserEvent(
                        user.getId().value()
                )
        );

        return user;
    }
}
