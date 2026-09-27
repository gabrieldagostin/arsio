package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.CreateGpuCommand;
import com.arsio.game.internal.domain.valueobject.Manufacturer;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGpuRequest;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface GpuControllerMapper {

    CreateGpuCommand toCreateGpuCommand(CreateGpuRequest request);

    default Manufacturer toManufacturer(String value) {
        return new Manufacturer(value);
    }

    default Model toModel(String value) {
        return new Model(value);
    }
}
