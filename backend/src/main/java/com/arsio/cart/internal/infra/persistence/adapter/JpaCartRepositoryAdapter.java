package com.arsio.cart.internal.infra.persistence.adapter;

import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.repository.CartRepository;
import com.arsio.cart.internal.domain.valueobject.CartStatus;
import com.arsio.cart.internal.infra.persistence.mapper.CartEntityMapper;
import com.arsio.cart.internal.infra.persistence.repository.SpringDataCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaCartRepositoryAdapter implements CartRepository {

    private final CartEntityMapper mapper;
    private final SpringDataCartRepository carts;

    @Override
    public Optional<Cart> findByUserIdAndStatus(UUID userId, CartStatus status) {
        return carts.findByUserIdAndStatus(userId, status)
                .map(mapper::toDomain);
    }

    @Override
    public Cart save(Cart cart) {
        carts.save(mapper.toEntity(cart));
        return cart;
    }
}
