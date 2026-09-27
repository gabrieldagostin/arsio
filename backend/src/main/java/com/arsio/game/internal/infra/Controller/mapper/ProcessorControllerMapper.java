package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.CreateProcessorCommand;
import com.arsio.game.internal.application.command.UpdateProcessorCommand;
import com.arsio.game.internal.domain.valueobject.Manufacturer;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.domain.valueobject.RequirementId;
import com.arsio.game.internal.infra.Controller.dto.request.CreateProcessorRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateProcessorRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface ProcessorControllerMapper {

    CreateProcessorCommand toCreateProcessorCommand(CreateProcessorRequest request);

    UpdateProcessorCommand toUpdateProcessorCommand(UUID processorId, UpdateProcessorRequest request);

    default RequirementId toRequirementId(UUID value) {
        return new RequirementId(value);
    }

    default Model toModel(String value) {
        return new Model(value);
    }

    default Manufacturer toManufacturer(String value) {
        return new Manufacturer(value);
    }
}
