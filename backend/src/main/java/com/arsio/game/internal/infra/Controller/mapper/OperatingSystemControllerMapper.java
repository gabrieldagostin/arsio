package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.CreateOperatingSystemCommand;
import com.arsio.game.internal.domain.valueobject.Model;
import com.arsio.game.internal.infra.Controller.dto.request.CreateOperatingSystemRequest;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface OperatingSystemControllerMapper {

    CreateOperatingSystemCommand toCreateOperatingSystemCommand(CreateOperatingSystemRequest request);

    default Model toModel(String value) {
        return new Model(value);
    }
}
