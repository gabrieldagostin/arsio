package com.arsio.user.api.dto;

import com.arsio.user.internal.domain.model.enums.UserRole;

import java.util.UUID;

public record UserAuthenticationData(
        UUID id,
        String username,
        String email,
        String passwordHash,
        UserRole role
) {}
