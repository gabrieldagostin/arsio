package com.arsio.auth.internal.application.command;

import com.arsio.auth.internal.domain.valueobject.PasswordToken;

public record ResetPasswordCommand(
        PasswordToken passwordToken,
        String newPassword
) {
}
