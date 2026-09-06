package com.arsio.user.internal.application.usecase;

import com.arsio.user.api.exception.UserNotFoundException;
import com.arsio.user.internal.application.port.output.FileStorage;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.model.record.PageResult;
import com.arsio.user.internal.domain.model.record.Pagination;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.repository.UserRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.ListUserResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ListUsersUseCase {

    private final UserRepository users;
    private final ProfileRepository profiles;
    private final FileStorage fileStorage;

    public ListUsersUseCase(UserRepository users, ProfileRepository profiles, FileStorage fileStorage) {
        this.users = users;
        this.profiles = profiles;
        this.fileStorage = fileStorage;
    }

    public PageResult<ListUserResponse> execute(String search, Pagination pagination) {

        return users.findAll(search, pagination)
                .map(user -> {

                    UserId userId = user.getId();

                    Profile profile = profiles.findByUserId(userId)
                            .orElseThrow(UserNotFoundException::new);

                    String avatarUrl = profile.getAvatarObjectKey() != null
                            ? fileStorage.generatePresignedDownloadUrl(
                                    profile.getAvatarObjectKey().value()
                    )
                            : null;

                    return new ListUserResponse(
                            user.getId().value(),
                            profile.getDisplayName().value(),
                            avatarUrl
                    );
                });
    }
}
