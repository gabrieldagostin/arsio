package com.arsio.cart.internal.infra.persistence.mapper;

import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.model.CartItem;
import com.arsio.cart.internal.domain.valueobject.CartStatus;
import com.arsio.cart.internal.domain.valueobject.CartId;
import com.arsio.cart.internal.infra.persistence.entity.CartEntity;
import com.arsio.cart.internal.infra.persistence.entity.CartItemEntity;
import com.arsio.config.mapper.CentralMapperConfig;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface CartEntityMapper {

    @Mapping(target = "cartItems", source = "items")
    @Mapping(target = "createdAt", ignore = true)
    CartEntity toEntity(Cart cart);

    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CartItemEntity toEntity(CartItem cartItem);

    @AfterMapping
    default void linkCartItems(@MappingTarget CartEntity cartEntity) {
        if (cartEntity.getCartItems() != null) {
            cartEntity.getCartItems().forEach(item -> item.setCart(cartEntity));
        }
    }

    @Mapping(target = "items", source = "cartItems")
    Cart toDomain(CartEntity cartEntity);

    CartItem toDomain(CartItemEntity cartItemEntity);

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
