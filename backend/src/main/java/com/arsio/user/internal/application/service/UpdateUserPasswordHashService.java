package com.arsio.user.internal.application.service;

import com.arsio.user.internal.application.dto.UpdatePasswordCommand;
import com.arsio.user.internal.domain.exception.UserNotFoundException;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.api.dto.UpdateUserPasswordHashCommand;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.PasswordHash;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UpdateUserPasswordHashService {

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserPasswordHashService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(UpdateUserPasswordHashCommand command) {

        UserId userId = new UserId(command.id());
        User user = users.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.value()));

        user.updatePassword(new PasswordHash(command.passwordHash()));

        users.save(user);
    }

    public void execute(UUID id, UpdatePasswordCommand command) {

        UserId userId = new UserId(id);
        User user = users.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.value()));

        String newPassword =  passwordEncoder.encode(command.newPassword());
        PasswordHash passwordHash = new PasswordHash(newPassword);
        user.updatePassword(passwordHash);

        users.save(user);
    }
}
