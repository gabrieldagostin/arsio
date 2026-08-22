package com.arsio.user.internal.application.service;

import com.arsio.user.internal.domain.exception.FriendshipNotFoundException;
import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.repository.FriendRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AcceptFriendRequestService {

    private final FriendRepository friends;

    public AcceptFriendRequestService(FriendRepository friends) {
        this.friends = friends;
    }

    public void execute(UUID friendshipId) {

        Friendship friendship = friends.findById(friendshipId)
                .orElseThrow(FriendshipNotFoundException::new);

        friendship.acceptRequest();

        friends.save(friendship);
    }
}
