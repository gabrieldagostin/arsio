package com.arsio.user.internal.application.dto;

public record UpdatePasswordCommand(
        String password,
        String newPassword
) {
}
