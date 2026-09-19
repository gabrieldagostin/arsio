package com.arsio.game.internal.application.command;

import com.arsio.game.internal.domain.valueobject.TagId;

import java.util.Set;

public record AssociateTagsCommand(
        Set<TagId> tagIds
) {
}
