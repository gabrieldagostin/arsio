package com.arsio.cart.internal.infra.persistence.mapper;

import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.model.CartItem;
import com.arsio.cart.internal.domain.valueobject.CartStatus;
import com.arsio.cart.internal.domain.valueobject.CartId;
import com.arsio.cart.internal.infra.persistence.entity.CartEntity;
import com.arsio.cart.internal.infra.persistence.entity.CartItemEmbeddable;
import com.arsio.config.mapper.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface CartEntityMapper {

    @Mapping(target = "createdAt", ignore = true)
    CartEntity toEntity(Cart cart);

    CartItemEmbeddable toEntity(CartItem cartItem);

    Cart toDomain(CartEntity cartEntity);

    CartItem toDomain(CartItemEmbeddable cartItemEmbeddable);

    default UUID cartIdToUuid(CartId cartId) {
        return cartId.value();
    }

    default CartId uuidToCartId(UUID value) {
        return new CartId(value);
    }

    default String cartStatusToString(CartStatus cartStatus) {
        return cartStatus.name();
    }

    default CartStatus stringToCartStatus(String value) {
        return CartStatus.valueOf(value);
    }
}
