package com.arsio.cart.internal.infra.persistence.adapter;

import com.arsio.cart.internal.domain.model.Cart;
import com.arsio.cart.internal.domain.repository.CartRepository;
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
    public Optional<Cart> findByUserId(UUID userId) {
        return carts.findByUserId(userId)
                .map(mapper::toDomain);
    }
}
