package com.arsio.user.internal.application.command;

import com.arsio.user.internal.domain.valueobject.PasswordHash;

public record UpdatePasswordCommand(
        PasswordHash password,
        PasswordHash newPassword
) {
}
