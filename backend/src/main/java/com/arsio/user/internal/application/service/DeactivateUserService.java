package com.arsio.user.internal.application.service;

import com.arsio.user.api.exception.UserNotFoundException;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeactivateUserService {

    private final UserRepository users;

    public DeactivateUserService(UserRepository users) {
        this.users = users;
    }

    public void execute(UUID id) {

        UserId userId = new UserId(id);
        User user = users.findById(userId).orElseThrow(UserNotFoundException::new);

        user.deactivate();

        users.save(user);
    }
}
