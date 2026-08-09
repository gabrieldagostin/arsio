package com.arsio.user.internal.application.service;

import com.arsio.user.internal.domain.exception.UserNotFoundException;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.CurrentUserResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GetCurrentUserService {

    private final UserRepository users;

    public GetCurrentUserService(UserRepository users) {
        this.users = users;
    }

    public CurrentUserResponse execute(UUID id) {

        UserId userId = new UserId(id);
        User user = users.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return new CurrentUserResponse(
                user.getId().value(),
                user.getUsername().value(),
                user.getEmail().value(),
                user.getRole().name()
        );
    }
}
