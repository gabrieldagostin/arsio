package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.domain.model.GameBuild;
import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.game.internal.infra.persistence.entity.GameBuildEntity;
import com.arsio.shared.storage.ObjectKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface GameBuildEntityMapper {

    @Mapping(target = "id", source = "gameBuildEntity.id")
    @Mapping(target = "gameId", source = "gameBuildEntity.game.id")
    GameBuild toDomain(GameBuildEntity gameBuildEntity);

    @Mapping(target = "id", source = "gameBuild.id")
    @Mapping(target = "game", source = "gameBuildEntity")
    @Mapping(target = "publishedAt", ignore = true)
    GameBuildEntity toEntity(GameBuild gameBuild, GameBuildEntity gameBuildEntity);

    default UUID gameBuildIdToUuid(GameBuildId gameBuildId) {
        return gameBuildId.value();
    }

    default GameBuildId uuidToGameBuildId(UUID value) {
        return new GameBuildId(value);
    }

    default UUID gameIdToUuid(GameId gameId) {
        return gameId.value();
    }

    default GameId uuidToGameId(UUID value) {
        return new GameId(value);
    }

    default String gameVersionToString(GameVersion gameVersion) {
        return gameVersion.value();
    }

    default GameVersion stringToGameVersion(String value) {
        return new GameVersion(value);
    }

    default String objectKeyToString(ObjectKey objectKey) {
        return objectKey.value();
    }

    default ObjectKey stringToObjectKey(String value) {
        return new ObjectKey(value);
    }

    default Integer fileSizeToInteger(FileSize fileSize) {
        return fileSize.value();
    }

    default FileSize integerToFileSize(Integer integer) {
        return new FileSize(integer);
    }

    default String buildStatusToString(BuildStatus buildStatus) {
        return buildStatus.name();
    }

    default BuildStatus stringToBuildStatus(String value) {
        return BuildStatus.valueOf(value);
    }
}
