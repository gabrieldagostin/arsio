package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.CreateOperatingSystemCommand;
import com.arsio.game.internal.application.command.UpdateOperatingSystemCommand;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.Controller.dto.request.CreateOperatingSystemRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateOperatingSystemRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface OperatingSystemControllerMapper {

    CreateOperatingSystemCommand toCreateOperatingSystemCommand(CreateOperatingSystemRequest request);

    UpdateOperatingSystemCommand toUpdateOperatingSystemCommand(UUID operatingSystemId, UpdateOperatingSystemRequest request);

    default RequirementId toRequirementId(UUID value) {
        return new RequirementId(value);
    }

    default Model toModel(String value) {
        return new Model(value);
    }
}
