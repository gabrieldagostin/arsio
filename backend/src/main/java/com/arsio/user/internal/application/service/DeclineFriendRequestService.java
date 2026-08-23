package com.arsio.user.internal.application.service;

import com.arsio.user.internal.domain.exception.FriendshipNotFoundException;
import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.repository.FriendshipRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeclineFriendRequestService {

    private final FriendshipRepository friendships;

    public DeclineFriendRequestService(FriendshipRepository friendships) {
        this.friendships = friendships;
    }

    public void execute(UUID friendshipId) {

        Friendship friendship = friendships.findById(friendshipId)
                .orElseThrow(FriendshipNotFoundException::new);

        friendship.declineRequest();

        friendships.save(friendship);
    }
}
