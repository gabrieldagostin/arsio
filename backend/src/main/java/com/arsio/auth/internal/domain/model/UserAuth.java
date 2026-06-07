package com.arsio.auth.internal.domain.model;

import java.util.UUID;

public class UserAuth {

    private final UUID id;
    private String username;
    private String email;
    private String passwordHash;
    private String role;
    private String profileImageKey;

    public UserAuth(UUID id, String username, String email, String passwordHash, String role, String profileImageKey) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.profileImageKey = profileImageKey;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getUserRole() {
        return role;
    }

    public String getProfileImageKey() {
        return profileImageKey;
    }
}

