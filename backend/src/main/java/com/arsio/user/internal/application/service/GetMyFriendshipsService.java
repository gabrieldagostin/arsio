package com.arsio.user.internal.application.service;

import com.arsio.user.api.exception.UserNotFoundException;
import com.arsio.user.internal.application.port.output.FileStorage;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.model.record.PageResult;
import com.arsio.user.internal.domain.model.record.Pagination;
import com.arsio.user.internal.domain.repository.FriendshipRepository;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.FriendResponse;
import com.arsio.user.internal.infra.controller.dto.response.GetMyFriendshipsResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GetMyFriendshipsService {

    private final FriendshipRepository friendships;
    private final ProfileRepository profiles;
    private final FileStorage fileStorage;

    public GetMyFriendshipsService(FriendshipRepository friendships, ProfileRepository profiles, FileStorage fileStorage) {
        this.friendships = friendships;
        this.profiles = profiles;
        this.fileStorage = fileStorage;
    }

    public PageResult<GetMyFriendshipsResponse> execute(UUID id, Pagination pagination) {

        UserId userId = new UserId(id);

        return friendships.findAcceptedByUserId(userId, pagination)
                .map(friendship -> {

                    UserId friendId = friendship.getOtherUser(userId);

                    Profile friendProfile = profiles.findByUserId(friendId)
                            .orElseThrow(UserNotFoundException::new);

                    String avatarUrl = friendProfile.getAvatarObjectKey() != null
                            ? fileStorage.generatePresignedDownloadUrl(
                            friendProfile.getAvatarObjectKey().value()
                    )
                            : null;

                    FriendResponse response = new FriendResponse(
                            friendProfile.getUserId().value(),
                            friendProfile.getDisplayName().value(),
                            avatarUrl
                    );

                    return new GetMyFriendshipsResponse(
                            friendship.getId().value(),
                            response
                    );
                });
    }
}
