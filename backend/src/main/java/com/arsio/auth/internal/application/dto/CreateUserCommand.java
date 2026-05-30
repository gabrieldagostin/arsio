package com.arsio.auth.internal.application.dto;

public record CreateUserCommand(
        String username,
        String email,
        String password
) {
}
