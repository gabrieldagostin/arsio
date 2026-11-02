package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.Discount;
import com.arsio.game.internal.domain.repository.DiscountRepository;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.mapper.DiscountEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataDiscountRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaDiscountRepositoryAdapter implements DiscountRepository {

    private final DiscountEntityMapper mapper;
    private final EntityManager entityManager;
    private final SpringDataDiscountRepository discounts;

    @Override
    public void save(Discount discount) {

        GameEntity gameEntity = entityManager.getReference(
                GameEntity.class,
                discount.getGameId()
        );

        discounts.save(mapper.toEntity(discount, gameEntity));
    }
}
