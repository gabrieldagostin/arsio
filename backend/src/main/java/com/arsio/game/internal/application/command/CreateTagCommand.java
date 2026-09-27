package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.TagName;

public record CreateTagCommand(
        TagName name
) {
}
