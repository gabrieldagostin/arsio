package com.arsio.user.internal.application.service;

import com.arsio.user.internal.domain.exception.UserNotFoundException;
import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.model.Profile;
import com.arsio.user.internal.domain.repository.FriendshipRepository;
import com.arsio.user.internal.domain.repository.ProfileRepository;
import com.arsio.user.internal.domain.valueobject.UserId;
import com.arsio.user.internal.infra.controller.dto.response.FriendResponse;
import com.arsio.user.internal.infra.controller.dto.response.GetMyFriendshipsResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GetMyFriendshipsSendsService {

    private final FriendshipRepository friendships;
    private final ProfileRepository profiles;

    public GetMyFriendshipsSendsService(FriendshipRepository friendships, ProfileRepository profiles) {
        this.friendships = friendships;
        this.profiles = profiles;
    }

    public List<GetMyFriendshipsResponse> execute(UUID id) {

        UserId userId = new UserId(id);

        List<Friendship> friendshipList = friendships.findPendingSendByUserId(userId);

        return friendshipList.stream()
                .map(friendship -> {

                    UserId addresseeId = friendship.getAddresseeId();

                    Profile friendProfile = profiles.findByUserId(addresseeId)
                            .orElseThrow(UserNotFoundException::new);

                    FriendResponse response = new FriendResponse(
                            friendProfile.getUserId().value(),
                            friendProfile.getDisplayName().value(),
                            friendProfile.getAvatarObjectKey().value()
                    );

                    return new GetMyFriendshipsResponse(
                            friendship.getId().value(),
                            response
                    );
                })
                .toList();
    }
}
