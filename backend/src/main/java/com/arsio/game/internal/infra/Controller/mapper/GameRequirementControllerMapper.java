package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.CreateGameRequirementCommand;
import com.arsio.game.internal.domain.valueobject.*;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGameRequirementRequest;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface GameRequirementControllerMapper {

    CreateGameRequirementCommand toCreateGameRequirementCommand(UUID gameId, CreateGameRequirementRequest request);

    default GameId toGameId(UUID value) {
        return new GameId(value);
    }

    default RequirementId toRequirementId(UUID value) {
        return value != null ? new RequirementId(value) : null;
    }

    default RequirementCategory toRequirementCategory(String value) {
        return RequirementCategory.valueOf(value);
    }

    default Ram toRam(Integer value) {
        return value != null ? new Ram(value) : null;
    }

    default Storage toStorage(BigDecimal value) {
        return value != null ? new Storage(value) : null;
    }

    default StorageUnit toStorageUnit(String value) {
        return value != null ? StorageUnit.valueOf(value) : null;
    }
}
