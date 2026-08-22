package com.arsio.user.internal.domain.model;

import com.arsio.user.internal.domain.model.enums.FriendshipStatus;
import com.arsio.user.internal.domain.valueobject.FriendshipId;
import com.arsio.user.internal.domain.valueobject.UserId;

import java.util.UUID;

public class Friendship {

    private final FriendshipId id;
    private UserId userId;
    private UserId friendId;
    private FriendshipStatus status;

    public Friendship(FriendshipId id, UserId userId, UserId friendId, FriendshipStatus status) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
    }

    public static Friendship create(UUID uId, UUID fId) {

        FriendshipId id = FriendshipId.generate();
        UserId userId = new UserId(uId);
        UserId friendId = new UserId(fId);
        FriendshipStatus status = FriendshipStatus.PENDING;

        return new Friendship(
                id,
                userId,
                friendId,
                status
        );
    }

    public FriendshipId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public UserId getFriendId() {
        return friendId;
    }

    public FriendshipStatus getStatus() {
        return status;
    }
}
