package com.arsio.user.internal.application.command;

import com.arsio.user.internal.domain.valueobject.Username;

public record UpdateUsernameCommand(
        Username newUsername
) {
}
