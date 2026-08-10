package com.arsio.user.internal.application.service;

import com.arsio.user.internal.domain.exception.ProfileNotFoundException;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.GetProfileResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GetUserProfileService {

    private final ProfileRepository profiles;

    public GetUserProfileService(ProfileRepository profiles) {
        this.profiles = profiles;
    }

    public GetProfileResponse execute(UUID value) {

        UserId userId = new UserId(value);
        Profile profile = profiles.findByUserId(userId)
                .orElseThrow(ProfileNotFoundException::new);

        String country = profile.getCountry().getFlag() + " " + profile.getCountry().getName();

        return new GetProfileResponse(
                profile.getDisplayName().value(),
                profile.getBio().value(),
                profile.getProfileImageKey().value(),
                profile.getProfileBannerKey().value(),
                country
        );
    }
}
