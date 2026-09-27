package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.TagId;
import com.arsio.game.internal.domain.valueobject.TagName;

public record UpdateTagCommand(
        TagId tagId,
        TagName name
) {
}
