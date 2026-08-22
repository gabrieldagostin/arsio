package com.arsio.user.internal.domain.model.enums;

import com.arsio.user.internal.domain.exception.InvalidUserRoleException;

public enum UserRole {

    ADMIN(0, "Admin"),
    USER(1, "User"),
    DEV(2, "Dev");

    private final Integer cod;
    private final String description;

    UserRole(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public UserRole toEnum(Integer cod) {
        if (cod == null) return null;
        for (UserRole userRole : UserRole.values()) {
            if (cod.equals(userRole.cod)) return userRole;
        }
        throw new InvalidUserRoleException();
    }
}
