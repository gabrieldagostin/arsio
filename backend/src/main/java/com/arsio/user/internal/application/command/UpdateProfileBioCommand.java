package com.arsio.user.internal.application.command;

import com.arsio.user.internal.domain.valueobject.Bio;

public record UpdateProfileBioCommand(
        Bio newBio
) {
}
