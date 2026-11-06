package com.arsio.user.internal.application.service;

import com.arsio.user.api.dto.GetUserSummaryView;
import com.arsio.user.api.exception.UserNotFoundException;
import com.arsio.user.internal.application.port.output.UserFileStorage;
import com.arsio.user.internal.domain.exception.ProfileNotFoundException;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.model.User;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class GetUserSummaryService {

    private final UserRepository users;
    private final ProfileRepository profiles;
    private final UserFileStorage userFileStorage;

    public GetUserSummaryService(UserRepository users, ProfileRepository profiles, UserFileStorage userFileStorage) {
        this.users = users;
        this.profiles = profiles;
        this.userFileStorage = userFileStorage;
    }

    public Optional<GetUserSummaryView> execute(UUID value) {

        UserId userId = new UserId(value);

        User user = users.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Profile profile = profiles.findByUserId(user.getId())
                .orElseThrow(ProfileNotFoundException::new);

        String avatarUrl = profile.getAvatarObjectKey() != null
                ? userFileStorage.generatePresignedDownloadUrl(
                profile.getAvatarObjectKey().value()
        )
                : null;

        return Optional.of(new GetUserSummaryView(
                user.getId().value(),
                profile.getDisplayName().value(),
                avatarUrl
        ));
    }
}
