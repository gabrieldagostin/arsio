package com.arsio.user.internal.domain.repository;

import com.arsio.user.internal.domain.model.Friendship;
import com.arsio.user.internal.domain.model.record.PageResult;
import com.arsio.user.internal.domain.model.record.Pagination;
import com.arsio.user.internal.domain.valueobject.FriendshipId;
import com.arsio.user.internal.domain.valueobject.UserId;

import java.util.Optional;

public interface FriendshipRepository {

    void save(Friendship friendship);

    Optional<Friendship> findById(FriendshipId friendshipId);

    PageResult<Friendship> findAcceptedByUserId(UserId userId, Pagination pagination);

    PageResult<Friendship> findPendingReceivedByUserId(UserId userId, Pagination pagination);

    PageResult<Friendship> findPendingSendByUserId(UserId userId, Pagination pagination);
}
