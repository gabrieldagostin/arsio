package com.arsio.user.internal.application.usecase;

import com.arsio.user.internal.application.port.output.UserFileStorage;
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
public class GetUserProfileUseCase {

    private final ProfileRepository profiles;
    private final UserFileStorage userFileStorage;

    public GetUserProfileUseCase(ProfileRepository profiles, UserFileStorage userFileStorage) {
        this.profiles = profiles;
        this.userFileStorage = userFileStorage;
    }

    public GetProfileResponse execute(UUID value) {

        UserId userId = new UserId(value);
        Profile profile = profiles.findByUserId(userId)
                .orElseThrow(ProfileNotFoundException::new);

        String country = profile.getCountry() != null
                ? profile.getCountry().getFlag() + " " + profile.getCountry().getName()
                : null;

        String avatarUrl = profile.getAvatarObjectKey() != null
                ? userFileStorage.generatePresignedDownloadUrl(
                profile.getAvatarObjectKey().value()
        )
                : null;

        String bannerUrl = profile.getBannerObjectKey() != null
                ? userFileStorage.generatePresignedDownloadUrl(
                profile.getBannerObjectKey().value()
        )
                : null;

        return new GetProfileResponse(
                profile.getDisplayName().value(),
                profile.getBio().value(),
                avatarUrl,
                bannerUrl,
                country
        );
    }
}
