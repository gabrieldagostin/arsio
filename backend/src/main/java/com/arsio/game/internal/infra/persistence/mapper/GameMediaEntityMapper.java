package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GameMediaId;
import com.arsio.game.internal.domain.valueobject.MediaType;
import com.arsio.game.internal.domain.valueobject.ObjectKey;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.entity.GameMediaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface GameMediaEntityMapper {

    @Mapping(target = "id", source = "gameMediaEntity.id")
    @Mapping(target = "gameId", source = "gameMediaEntity.game.id")
    GameMedia toDomain(GameMediaEntity gameMediaEntity);

    @Mapping(target = "id", source = "gameMedia.id")
    @Mapping(target = "game", source = "gameEntity")
    @Mapping(target = "createdAt", ignore = true)
    GameMediaEntity toEntity(GameMedia gameMedia, GameEntity gameEntity);

    default UUID gameMediaIdToUuid(GameMediaId gameMediaId) {
        return gameMediaId.value();
    }

    default GameMediaId uuidToGameMediaId(UUID value) {
        return new GameMediaId(value);
    }

    default UUID gameIdToUuid(GameId gameId) {
        return gameId.value();
    }

    default GameId uuidToGameId(UUID value) {
        return new GameId(value);
    }

    default String objectKeyToString(ObjectKey objectKey) {
        return objectKey.value();
    }

    default ObjectKey stringToObjectKey(String value) {
        return new ObjectKey(value);
    }

    default String mediaTypeToString(MediaType mediaType) {
        return mediaType.name();
    }

    default MediaType stringToMediaType(String value) {
        return MediaType.valueOf(value);
    }
}
