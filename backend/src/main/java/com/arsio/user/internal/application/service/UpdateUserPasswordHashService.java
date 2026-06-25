package com.arsio.user.internal.application.service;

import com.arsio.user.api.dto.UpdateUserPasswordHashCommand;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.Password;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserPasswordHashService {

    private final UserRepository users;

    public UpdateUserPasswordHashService(UserRepository users) {
        this.users = users;
    }

    public void execute(UpdateUserPasswordHashCommand command) {

        User user = users.findById(command.id());

        user.updatePassword(new Password(command.passwordHash()));

        users.save(user);
    }
}
