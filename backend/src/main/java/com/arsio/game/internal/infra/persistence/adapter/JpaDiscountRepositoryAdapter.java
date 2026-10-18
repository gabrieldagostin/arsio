package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.repository.DiscountRepository;
import com.arsio.game.internal.infra.persistence.mapper.DiscountEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataDiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaDiscountRepositoryAdapter implements DiscountRepository {

    private final DiscountEntityMapper mapper;
    private final SpringDataDiscountRepository discounts;
}
