package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.entity.GameMediaEntity;
import com.arsio.game.internal.infra.persistence.mapper.GameMediaEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGameMediaRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaGameMediaRepositoryAdapter implements GameMediaRepository {

    private final GameMediaEntityMapper mapper;
    private final SpringDataGameMediaRepository gameMediaRepository;
    private final EntityManager entityManager;

    @Override
    public void save(GameMedia gameMedia) {

        GameEntity gameEntity = entityManager.find(
                GameEntity.class,
                gameMedia.getGameId().value()
        );

        GameMediaEntity gameMediaEntity = mapper.toEntity(gameMedia, gameEntity);

        gameMediaRepository.save(gameMediaEntity);
    }

    @Override
    public Optional<GameMedia> findByGameId(GameId gameId) {
        return gameMediaRepository.findByGameId(gameId.value())
                .map(mapper::toDomain);
    }
}
