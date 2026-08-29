package com.arsio.auth.internal.application.command;

public record CreateUserCommand(
        String username,
        String email,
        String password
) {
}
