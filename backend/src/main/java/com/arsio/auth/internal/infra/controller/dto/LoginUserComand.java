package com.arsio.auth.internal.infra.controller.dto;

public record LoginUserComand(
        String username,
        String password
) {
}
