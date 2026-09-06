package com.arsio.user.internal.application.usecase;

import com.arsio.user.api.exception.UserNotFoundException;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.GetUserResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
@Transactional
public class GetUserByIdUseCase {

    private final UserRepository users;

    public GetUserByIdUseCase(UserRepository users) {
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
