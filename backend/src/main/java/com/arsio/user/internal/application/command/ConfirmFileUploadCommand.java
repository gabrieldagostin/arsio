package com.arsio.user.internal.application.command;

import com.arsio.user.internal.domain.valueobject.ObjectKey;

public record ConfirmFileUploadCommand(
        ObjectKey objectKey
) {
}
