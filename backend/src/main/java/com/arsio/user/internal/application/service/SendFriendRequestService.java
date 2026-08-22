package com.arsio.user.internal.application.service;

import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.repository.FriendRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class SendFriendRequestService {

    private final FriendRepository friends;

    public SendFriendRequestService(FriendRepository friends) {
        this.friends = friends;
    }

    public void execute(UUID userId, UUID friendId) {

        Friendship friendship = Friendship.create(userId, friendId);

        friends.save(friendship);
    }
}
