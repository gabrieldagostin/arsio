package com.arsio.wishlist.internal.infra.persistence.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.wishlist.internal.domain.model.Wishlist;
import com.arsio.wishlist.internal.domain.model.WishlistGame;
import com.arsio.wishlist.internal.domain.valueobject.WishlistId;
import com.arsio.wishlist.internal.infra.persistence.entity.WishlistEntity;
import com.arsio.wishlist.internal.infra.persistence.entity.WishlistGameEmbeddable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface WishlistEntityMapper {

    Wishlist toDomain(WishlistEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    WishlistEntity toEntity(Wishlist domain);

    WishlistGame toDomain(WishlistGameEmbeddable embeddable);

    WishlistGameEmbeddable toEntity(WishlistGame domainItem);

    default UUID whishlistIdToUuid(WishlistId wishlistId) {
        return wishlistId.value();
    }

    default WishlistId uuidToWishlistId(UUID value) {
        return new WishlistId(value);
    }
}
