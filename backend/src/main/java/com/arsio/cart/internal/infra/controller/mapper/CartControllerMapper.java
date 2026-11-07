package com.arsio.cart.internal.infra.controller.mapper;

import com.arsio.cart.internal.application.command.AddItemToCartCommand;
import com.arsio.cart.internal.application.command.RemoveItemFromCartCommand;
import com.arsio.cart.internal.infra.controller.dto.request.AddItemToCartRequest;
import com.arsio.config.mapper.CentralMapperConfig;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface CartControllerMapper {

    AddItemToCartCommand toAddItemToCartCommand(UUID userId, AddItemToCartRequest request);

    RemoveItemFromCartCommand toRemoveItemFromCartCommand(UUID userId, UUID gameId);
}
