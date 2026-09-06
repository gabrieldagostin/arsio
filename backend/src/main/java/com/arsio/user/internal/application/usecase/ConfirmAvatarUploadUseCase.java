package com.arsio.user.internal.application.usecase;

import com.arsio.user.internal.application.command.ConfirmFileUploadCommand;
import com.arsio.user.internal.application.port.output.FileStorage;
import com.arsio.user.internal.domain.exception.ProfileNotFoundException;
import com.arsio.user.internal.domain.model.record.ObjectMetadata;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.GetAvatarResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class ConfirmAvatarUploadUseCase {

    private static final long MAX_AVATAR_SIZE = 5 * 1024 * 1024;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/png",
            "image/jpeg",
            "image/webp"
    );

    private final FileStorage fileStorage;
    private final ProfileRepository profiles;

    public ConfirmAvatarUploadUseCase(FileStorage fileStorage, ProfileRepository profiles) {
        this.fileStorage = fileStorage;
        this.profiles = profiles;
    }

    public GetAvatarResponse execute(UUID userId, ConfirmFileUploadCommand command) {

        ObjectMetadata metadata = fileStorage.getObjectMetadata(command.objectKey().value());

        if (metadata.size() > MAX_AVATAR_SIZE) {
            throw new IllegalArgumentException(
                    "Avatar cannot exceed 5 MB"
            );
        }

        if (!ALLOWED_TYPES.contains(metadata.contentType())) {
            throw new IllegalArgumentException(
                    "Unsupported avatar format"
            );
        }

        Profile profile = profiles.findByUserId(new UserId(userId))
                .orElseThrow(ProfileNotFoundException::new);

        profile.updateAvatarObjectKey(command.objectKey());
        profiles.save(profile);

        String avatarUrl = fileStorage.generatePresignedDownloadUrl(profile.getAvatarObjectKey().value());

        return new GetAvatarResponse(avatarUrl);
    }
}
