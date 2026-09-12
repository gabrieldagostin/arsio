package com.arsio.game.internal.infra.persistence.mapper;

import com.arsio.game.internal.domain.model.GameRequirement;
import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.game.internal.infra.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface GameRequirementEntityMapper {

    @Mapping(target = "id", source = "gameRequirementEntity.id")
    @Mapping(target = "gameId", source = "gameRequirementEntity.game.id")
    @Mapping(target = "operatingSystemId", source = "gameRequirementEntity.operatingSystem.id")
    @Mapping(target = "processorId", source = "gameRequirementEntity.processor.id")
    @Mapping(target = "gpuId", source = "gameRequirementEntity.gpu.id")
    GameRequirement toDomain(GameRequirementEntity gameRequirementEntity);

    @Mapping(target = "id", source = "gameRequirement.id")
    @Mapping(target = "game", source = "gameEntity")
    @Mapping(target = "operatingSystem", source = "operatingSystemEntity")
    @Mapping(target = "processor", source = "processorEntity")
    @Mapping(target = "gpu", source = "gpuEntity")
    @Mapping(target = "createdAt", ignore = true)
    GameRequirementEntity toEntity(GameRequirement gameRequirement, GameEntity gameEntity, OperatingSystemEntity operatingSystemEntity, ProcessorEntity processorEntity, GpuEntity gpuEntity);

    default UUID requirementIdToUuid(RequirementId requirementId) {
        return requirementId.value();
    }

    default RequirementId uuidToRequirementId(UUID value) {
        return new RequirementId(value);
    }

    default UUID gameIdToUuid(GameId gameId) {
        return gameId.value();
    }

    default GameId uuidToGameId(UUID value) {
        return new GameId(value);
    }

    default String requirementCategory(RequirementCategory requirementCategory) {
        return requirementCategory.name();
    }

    default RequirementCategory stringToRequirementCategory(String value) {
        return RequirementCategory.valueOf(value);
    }

    default Integer ramToInteger(Ram ram) {
        return ram.value();
    }

    default Ram integerToRam(Integer value) {
        return new Ram(value);
    }

    default Double storageToDouble(Storage storage) {
        return storage.value();
    }

    default String storageUnitToString(StorageUnit storageUnit) {
        return storageUnit.name();
    }
    default StorageUnit stringToStorageUnit(String value) {
        return StorageUnit.valueOf(value);
    }
}
