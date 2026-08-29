package com.arsio.auth.internal.application.command;

public record LoginUserComand(
        String username,
        String password
) {
}
