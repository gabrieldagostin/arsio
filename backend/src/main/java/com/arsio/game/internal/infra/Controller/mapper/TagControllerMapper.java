package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.AssociateTagsCommand;
import com.arsio.game.internal.application.command.DeleteTagFromGameCommand;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.TagId;
import com.arsio.game.internal.infra.Controller.dto.request.AssociateTagsRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface TagControllerMapper {

    AssociateTagsCommand toAssociateTagsCommand(AssociateTagsRequest request);

    DeleteTagFromGameCommand toDeleteTagFromGameCommand(UUID gameId, UUID tagId);

    default TagId toTagId(UUID value) {
        return new TagId(value);
    }

    default GameId toGameId(UUID value) {
        return new GameId(value);
    }
}
