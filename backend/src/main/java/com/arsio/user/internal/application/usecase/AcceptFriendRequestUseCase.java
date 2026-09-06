package com.arsio.user.internal.application.usecase;

import com.arsio.user.internal.domain.exception.FriendshipNotFoundException;
import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.repository.FriendshipRepository;
import com.arsio.user.internal.domain.valueobject.FriendshipId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AcceptFriendRequestUseCase {

    private final FriendshipRepository friendships;

    public AcceptFriendRequestUseCase(FriendshipRepository friendships) {
        this.friendships = friendships;
    }

    public void execute(UUID id) {

        FriendshipId friendshipId = new FriendshipId(id);

        Friendship friendship = friendships.findById(friendshipId)
                .orElseThrow(FriendshipNotFoundException::new);

        friendship.acceptRequest();

        friendships.save(friendship);
    }
}
