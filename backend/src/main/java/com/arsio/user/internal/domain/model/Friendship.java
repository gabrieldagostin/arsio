package com.arsio.user.internal.domain.model;

import com.arsio.user.internal.domain.exception.InvalidFriendshipException;
import com.arsio.user.internal.domain.model.enums.FriendshipStatus;
import com.arsio.user.internal.domain.valueobject.FriendshipId;
import com.arsio.user.internal.domain.valueobject.UserId;

import java.util.UUID;

public class Friendship {

    private final FriendshipId id;
    private final UserId requesterId;
    private final UserId addresseeId;
    private FriendshipStatus status;

    public Friendship(FriendshipId id, UserId requesterId, UserId addresseeId, FriendshipStatus status) {
        this.id = id;
        this.requesterId = requesterId;
        this.addresseeId = addresseeId;
        this.status = status;
    }

    public static Friendship create(UUID uId, UUID fId) {

        FriendshipId id = FriendshipId.generate();
        UserId requesterId = new UserId(uId);
        UserId addresseeId = new UserId(fId);
        FriendshipStatus status = FriendshipStatus.PENDING;

        return new Friendship(
                id,
                requesterId,
                addresseeId,
                status
        );
    }

    public FriendshipId getId() {
        return id;
    }

    public UserId getRequesterId() {
        return requesterId;
    }

    public UserId getAddresseeId() {
        return addresseeId;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void acceptRequest() {
        status = FriendshipStatus.ACCEPTED;
    }

    public void declineRequest() {
        status = FriendshipStatus.REJECTED;
    }

    public UserId getOtherUser(UserId userId) {

        if (requesterId.equals(userId)) return addresseeId;

        if (addresseeId.equals(userId)) return requesterId;

        throw new InvalidFriendshipException();
    }
}
