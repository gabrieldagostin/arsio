package com.arsio.auth.internal.domain.model;

import com.arsio.shared.valueobject.UserId;

import java.util.UUID;

public class UserAuth {

    private final UserId id;
    private String username;
    private String email;
    private String passwordHash;
    private String role;
    private String profileImageKey;

    public UserAuth(UserId id, String username, String email, String passwordHash, String role, String profileImageKey) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.profileImageKey = profileImageKey;
    }

    public UserId getId() {
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

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public String getProfileImageKey() {
        return profileImageKey;
    }
}

