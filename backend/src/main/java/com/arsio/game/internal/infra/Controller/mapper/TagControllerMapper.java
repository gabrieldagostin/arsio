package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.AssociateTagsCommand;
import com.arsio.game.internal.domain.valueobject.TagId;
import com.arsio.game.internal.infra.Controller.dto.request.AssociateTagsRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface TagControllerMapper {

    AssociateTagsCommand toAssociateTagsCommand(AssociateTagsRequest request);

    default TagId toTagId(UUID value) {
        return new TagId(value);
    }
}
