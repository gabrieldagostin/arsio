package com.arsio.auth.internal.infra.controller.dto;

public record CreateUserCommand(
        String username,
        String email,
        String password
) {
}
