package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.entity.GameMediaEntity;
import com.arsio.shared.storage.ObjectKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config =  CentralMapperConfig.class)
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

    default String mediaRoleToString(MediaRole mediaRole) {
        return mediaRole.name();
    }

    default MediaRole stringToMediaRole(String value) {
        return MediaRole.valueOf(value);
    }

    default String externalUrlToString(ExternalUrl externalUrl) {
        return externalUrl.value();
    }

    default ExternalUrl stringToExternalUrl(String value) {
        return new ExternalUrl(value);
    }
}
