package com.arsio.user.internal.application.service;

import com.arsio.user.internal.domain.exception.UserNotFoundException;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.GetUserResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public class GetUserByIdService {

    private final UserRepository users;

    public GetUserByIdService(UserRepository users) {
        this.users = users;
    }

    public GetUserResponse execute(@PathVariable UUID id) {

        UserId userId = new UserId(id);
        User user = users.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));

        return new GetUserResponse(
                user.getId().value(),
                user.getUsername().value(),
                user.getEmail().value(),
                user.getRole().name()
        );
    }
}
