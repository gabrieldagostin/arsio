package com.arsio.user.internal.domain.model.enums;

import com.arsio.user.internal.domain.exception.InvalidFriendshipStatus;

public enum FriendshipStatus {

    ACCEPTED(0, "Accepted"),
    REJECTED(1, "Rejected"),
    PENDING(2, "Pending");

    private final Integer cod;
    private final String description;

    FriendshipStatus(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public FriendshipStatus toEnum() {
        if (cod == null) return null;
        for (FriendshipStatus friendshipStatus : FriendshipStatus.values()) {
            if (cod.equals(friendshipStatus.cod)) return friendshipStatus;
        }
        throw new InvalidFriendshipStatus();
    }
}
