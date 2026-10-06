package com.arsio.user.internal.application.usecase;

import com.arsio.shared.storage.ConfirmFileUploadCommand;
import com.arsio.user.internal.application.port.output.UserFileStorage;
import com.arsio.user.internal.domain.exception.ProfileNotFoundException;
import com.arsio.shared.storage.ObjectMetadata;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.GetBannerResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class ConfirmUserBannerUploadUseCase {

    private static final long MAX_BANNER_SIZE = 10 * 1024 * 1024;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/png",
            "image/jpeg",
            "image/webp"
    );

    private final UserFileStorage userFileStorage;
    private final ProfileRepository profiles;

    public ConfirmUserBannerUploadUseCase(UserFileStorage userFileStorage, ProfileRepository profiles) {
        this.userFileStorage = userFileStorage;
        this.profiles = profiles;
    }

    public GetBannerResponse execute(UUID userId, ConfirmFileUploadCommand command) {

        ObjectMetadata metadata = userFileStorage.getObjectMetadata(command.objectKey().value());

        if (metadata.size() > MAX_BANNER_SIZE) {
            throw new IllegalArgumentException(
                    "Banner cannot exceed 10 MB"
            );
        }

        if (!ALLOWED_TYPES.contains(metadata.contentType())) {
            throw new IllegalArgumentException(
                    "Unsupported banner format"
            );
        }

        Profile profile = profiles.findByUserId(new UserId(userId))
                .orElseThrow(ProfileNotFoundException::new);

        profile.updateBannerObjectKey(command.objectKey());
        profiles.save(profile);

        String avatarUrl = userFileStorage.generatePresignedDownloadUrl(profile.getBannerObjectKey().value());

        return new GetBannerResponse(avatarUrl);
    }
}
