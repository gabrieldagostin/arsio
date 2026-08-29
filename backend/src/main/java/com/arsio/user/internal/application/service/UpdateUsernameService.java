package com.arsio.user.internal.application.service;

import com.arsio.user.internal.application.command.UpdateUsernameCommand;
import com.arsio.user.internal.domain.exception.UserNotFoundException;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.domain.valueobject.Username;
import com.arsio.user.internal.infra.controller.dto.response.UpdateUsernameResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UpdateUsernameService {

    private final UserRepository users;

    public UpdateUsernameService(UserRepository users) {
        this.users = users;
    }

    public UpdateUsernameResponse execute(UUID id, UpdateUsernameCommand command) {

        UserId userId = new UserId(id);
        User user = users.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.updateUsername(command.newUsername());

        users.save(user);

        return new UpdateUsernameResponse(
                user.getUsername().value()
        );
    }
}
