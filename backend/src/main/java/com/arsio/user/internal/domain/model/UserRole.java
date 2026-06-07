package com.arsio.user.internal.domain.model;

import com.arsio.shared.exception.InvalidUserRoleException;

public enum UserRole {

    ADMIN(0, "Admin"),
    USER(1, "User"),
    DEV(2, "Dev");

    private Integer cod;
    private String description;

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
