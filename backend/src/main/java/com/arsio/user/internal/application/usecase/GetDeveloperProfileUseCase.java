package com.arsio.user.internal.application.usecase;

import com.arsio.user.api.dto.GetDeveloperProfileView;
import com.arsio.user.api.exception.UserNotFoundException;
import com.arsio.user.internal.application.port.output.UserFileStorage;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class GetDeveloperProfileUseCase {

    private final ProfileRepository profiles;
    private final UserFileStorage userFileStorage;

    public GetDeveloperProfileUseCase(ProfileRepository profiles, UserFileStorage userFileStorage) {
        this.profiles = profiles;
        this.userFileStorage = userFileStorage;
    }

    public Optional<GetDeveloperProfileView> execute(UUID developerId) {

        UserId userId = new UserId(developerId);

        Profile profile = profiles.findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);

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

        return Optional.of(new GetDeveloperProfileView(
                profile.getDisplayName().value(),
                profile.getBio().value(),
                avatarUrl,
                bannerUrl,
                profile.getCountry().getFlag() + profile.getCountry().getCode()
        ));
    }
}
