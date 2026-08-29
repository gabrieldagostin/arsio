package com.arsio.user.internal.application.service;

import com.arsio.user.internal.application.command.UpdateProfileBioCommand;
import com.arsio.user.internal.domain.exception.UserNotFoundException;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.Bio;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.UpdateProfileBioResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UpdateProfileBioService {

    private final ProfileRepository profiles;

    public UpdateProfileBioService(ProfileRepository profiles) {
        this.profiles = profiles;
    }

    public UpdateProfileBioResponse execute(UUID id, UpdateProfileBioCommand command) {

        UserId userId = new UserId(id);

        Profile profile = profiles.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

        profile.updateBio(command.newBio());

        profiles.save(profile);

        return new UpdateProfileBioResponse(
                profile.getBio().value()
        );
    }
}
