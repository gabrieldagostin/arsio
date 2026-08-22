package com.arsio.user.internal.domain.model.enums;

import com.arsio.user.internal.domain.exception.InvalidUserRoleException;

public enum UserStatus {

    ACTIVE(0, "Active"),
    INACTIVE(1, "Inactive"),
    PENDING(3, "Pending"),
    SUSPENDED(4, "Suspended"),
    BANNED(5, "Banned");

    private final Integer cod;
    private final String description;

    UserStatus(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public UserStatus toEnum(Integer cod) {
        if (cod == null) return null;
        for (UserStatus userStatus : UserStatus.values()) {
            if (cod.equals(userStatus.cod)) return userStatus;
        }
        throw new InvalidUserRoleException();
    }
}
