package com.arsio.user.internal.domain.model;

import com.arsio.user.internal.domain.exception.InvalidFriendshipException;
import com.arsio.user.internal.domain.model.enums.FriendshipStatus;
import com.arsio.user.internal.domain.valueobject.FriendshipId;
import com.arsio.user.internal.domain.valueobject.UserId;

import java.util.UUID;

public class Friendship {

    private final FriendshipId id;
    private UserId requesterId;
    private UserId addresseId;
    private FriendshipStatus status;

    public Friendship(FriendshipId id, UserId requesterId, UserId addresseId, FriendshipStatus status) {
        this.id = id;
        this.requesterId = requesterId;
        this.addresseId = addresseId;
        this.status = status;
    }

    public static Friendship create(UUID uId, UUID fId) {

        FriendshipId id = FriendshipId.generate();
        UserId requesterId = new UserId(uId);
        UserId addresseId = new UserId(fId);
        FriendshipStatus status = FriendshipStatus.PENDING;

        return new Friendship(
                id,
                requesterId,
                addresseId,
                status
        );
    }

    public FriendshipId getId() {
        return id;
    }

    public UserId getRequesterId() {
        return requesterId;
    }

    public UserId getAddresseId() {
        return addresseId;
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

        if (requesterId.equals(userId)) return addresseId;

        if (addresseId.equals(userId)) return requesterId;

        throw new InvalidFriendshipException();
    }
}
