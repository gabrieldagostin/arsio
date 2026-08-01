package com.arsio.user.internal.application.service;

import com.arsio.user.internal.domain.exception.UserNotFoundException;
import com.arsio.shared.valueobject.UserId;
import com.arsio.user.api.dto.UpdateUserPasswordHashCommand;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.PasswordHash;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserPasswordHashService {

    private final UserRepository users;

    public UpdateUserPasswordHashService(UserRepository users) {
        this.users = users;
    }

    public void execute(UpdateUserPasswordHashCommand command) {

        UserId userId = new UserId(command.id());

        User user = users.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.value()));

        user.updatePassword(new PasswordHash(command.passwordHash()));

        users.save(user);
    }
}
