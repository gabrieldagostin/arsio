package com.arsio.user.internal.application.usecase;

import com.arsio.user.internal.application.command.UpdateUsernameCommand;
import com.arsio.user.api.exception.UserNotFoundException;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.UpdateUsernameResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UpdateUsernameUseCase {

    private final UserRepository users;
    private final ProfileRepository profiles;

    public UpdateUsernameUseCase(UserRepository users, ProfileRepository profiles) {
        this.users = users;
        this.profiles = profiles;
    }

    public UpdateUsernameResponse execute(UUID id, UpdateUsernameCommand command) {

        UserId userId = new UserId(id);
        User user = users.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.updateUsername(command.newUsername());
        users.save(user);

        Profile profile = profiles.findByUserId(user.getId())
                .orElseThrow(UserNotFoundException::new);

        profile.updateDisplayName(command.newUsername());
        profiles.save(profile);

        return new UpdateUsernameResponse(
                profile.getDisplayName().value()
        );
    }
}
