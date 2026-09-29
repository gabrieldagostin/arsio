package com.arsio.user.internal.application.usecase;

import com.arsio.user.internal.application.port.output.UserFileStorage;
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
public class GetUserAvatarUseCase {

    private final ProfileRepository profiles;
    private final UserFileStorage userFileStorage;

    public GetUserAvatarUseCase(ProfileRepository profiles, UserFileStorage userFileStorage) {
        this.profiles = profiles;
        this.userFileStorage = userFileStorage;
    }

    public GetAvatarResponse execute(UUID value) {

        UserId userId = new UserId(value);
        Profile profile = profiles.findByUserId(userId)
                .orElseThrow(ProfileNotFoundException::new);

        String avatarUrl = profile.getAvatarObjectKey() != null
                ? userFileStorage.generatePresignedDownloadUrl(
                profile.getAvatarObjectKey().value()
        )
                : null;

        return new GetAvatarResponse(
                avatarUrl
        );
    }
}
