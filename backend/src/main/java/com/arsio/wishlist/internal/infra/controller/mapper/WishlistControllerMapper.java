package com.arsio.wishlist.internal.infra.controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.wishlist.internal.application.command.AddToWishlistCommand;
import com.arsio.wishlist.internal.application.command.DeleteWishlistGameCommand;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface WishlistControllerMapper {

    AddToWishlistCommand toAddToWishlistCommand(UUID userId, UUID gameId);

    DeleteWishlistGameCommand toDeleteWishlistGameCommand(UUID userId, UUID gameId);
}
