package com.arsio.user.internal.application.usecase;

import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.repository.FriendshipRepository;
import com.arsio.user.internal.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class SendFriendRequestUseCase {

    private final FriendshipRepository friendships;
    private final UserRepository users;

    public SendFriendRequestUseCase(FriendshipRepository friendships, UserRepository users) {
        this.friendships = friendships;
        this.users = users;
    }
    public void execute(UUID requesterId, UUID addresseeId) {

        Friendship friendship = Friendship.create(requesterId, addresseeId);

        friendships.save(friendship);
    }
}
