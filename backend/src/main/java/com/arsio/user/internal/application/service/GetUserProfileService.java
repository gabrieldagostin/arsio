package com.arsio.user.internal.application.service;

import com.arsio.user.internal.application.port.output.FileStorage;
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
    private final FileStorage fileStorage;

    public GetUserProfileService(ProfileRepository profiles, FileStorage fileStorage) {
        this.profiles = profiles;
        this.fileStorage = fileStorage;
    }

    public GetProfileResponse execute(UUID value) {

        UserId userId = new UserId(value);
        Profile profile = profiles.findByUserId(userId)
                .orElseThrow(ProfileNotFoundException::new);

        String country = profile.getCountry().getFlag() + " " + profile.getCountry().getName();

        String avatarUrl = fileStorage.generatePresignedUrl(profile.getAvatarObjectKey().value());
        String bannerUrl = fileStorage.generatePresignedUrl(profile.getBannerObjectKey().value());

        return new GetProfileResponse(
                profile.getDisplayName().value(),
                profile.getBio().value(),
                avatarUrl,
                bannerUrl,
                country
        );
    }
}
