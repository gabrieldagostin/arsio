package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.CreateGpuCommand;
import com.arsio.game.internal.application.command.UpdateGpuCommand;
import com.arsio.game.internal.domain.valueobject.Manufacturer;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGpuRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateGpuRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface GpuControllerMapper {

    CreateGpuCommand toCreateGpuCommand(CreateGpuRequest request);

    UpdateGpuCommand toUpdateGpuCommand(UUID gpuId, UpdateGpuRequest request);

    default RequirementId toRequirementId(UUID value) {
        return new RequirementId(value);
    }

    default Manufacturer toManufacturer(String value) {
        return new Manufacturer(value);
    }

    default Model toModel(String value) {
        return new Model(value);
    }
}
