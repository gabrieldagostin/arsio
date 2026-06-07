package com.arsio.user.api.dto;

public record CreateUserCommand(
        String username,
        String email,
        String password
) {
}
