package com.arsio.developer.internal.domain.model;

import com.arsio.developer.internal.domain.valueobject.DeveloperId;
import com.arsio.developer.internal.domain.valueobject.MpAccessToken;

public class Developer {

    private final DeveloperId id;
    private String username;
    private String email;
    private String passwordHash;
    private String role;
    private MpAccessToken mpAccessToken;
    private String status;

    public Developer(DeveloperId id, String username, String email, String passwordHash, String role, MpAccessToken mpAccessToken, String status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.mpAccessToken = mpAccessToken;
        this.status = status;
    }

    public DeveloperId getId() {
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

    public String getRole() {
        return role;
    }

    public MpAccessToken getMpAccessToken() {
        return mpAccessToken;
    }

    public String getStatus() {
        return status;
    }
}
