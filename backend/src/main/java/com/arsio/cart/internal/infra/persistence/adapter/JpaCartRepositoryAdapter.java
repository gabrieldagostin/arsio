package com.arsio.cart.internal.infra.persistence.adapter;

import com.arsio.cart.internal.domain.repository.CartRepository;
import com.arsio.cart.internal.infra.persistence.mapper.CartEntityMapper;
import com.arsio.cart.internal.infra.persistence.repository.SpringDataCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaCartRepositoryAdapter implements CartRepository {

    private final CartEntityMapper mapper;
    private final SpringDataCartRepository carts;
}
