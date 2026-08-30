package com.arsio.auth.internal.application.command;

public record LoginUserCommand(
        String username,
        String password
) {
}
