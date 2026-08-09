package com.arsio.user.internal.application.service;

import com.arsio.user.internal.domain.exception.ProfileNotFoundException;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.GetAvatarResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GetUserAvatarService {

    private final ProfileRepository profiles;

    public GetUserAvatarService(ProfileRepository profiles) {
        this.profiles = profiles;
    }

    public GetAvatarResponse execute(UUID value) {

        UserId userId = new UserId(value);
        Profile profile = profiles.findByUserId(userId)
                .orElseThrow(ProfileNotFoundException::new);

        return new GetAvatarResponse(profile.getProfileImageKey().value());
    }
}
