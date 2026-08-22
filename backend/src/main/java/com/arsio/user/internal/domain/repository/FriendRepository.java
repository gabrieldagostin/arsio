package com.arsio.user.internal.domain.repository;

import com.arsio.user.internal.domain.model.Friendship;

import java.util.Optional;
import java.util.UUID;

public interface FriendRepository {

    void save(Friendship friendship);

    Optional<Friendship> findById(UUID friendshipId);
}
