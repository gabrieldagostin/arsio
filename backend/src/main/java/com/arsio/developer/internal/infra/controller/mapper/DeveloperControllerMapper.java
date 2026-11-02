package com.arsio.developer.internal.infra.controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.developer.internal.application.command.ApplyGameDiscountCommand;
import com.arsio.developer.internal.infra.controller.dto.request.ApplyGameDiscountRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface DeveloperControllerMapper {

    ApplyGameDiscountCommand toApplyGameDiscountCommand(UUID gameId, ApplyGameDiscountRequest request);
}
