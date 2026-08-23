package com.arsio.user.internal.domain.repository;

import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.valueobject.FriendshipId;
import com.arsio.user.internal.domain.valueobject.UserId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FriendshipRepository {

    void save(Friendship friendship);

    Optional<Friendship> findById(FriendshipId friendshipId);

    List<Friendship> findAcceptedByUserId(UserId userId);

    List<Friendship> findPendingReceivedByUserId(UserId userId);

    List<Friendship> findPendingSendByUserId(UserId userId);
}
