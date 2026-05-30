package com.arsio.auth.internal.application.dto;

public record LoginUserComand(
        String username,
        String password
) {
}
