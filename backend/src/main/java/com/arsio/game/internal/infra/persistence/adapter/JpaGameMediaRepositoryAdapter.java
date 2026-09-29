package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.domain.valueobject.GameMediaId;
import com.arsio.game.internal.domain.valueobject.MediaRole;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.entity.GameMediaEntity;
import com.arsio.game.internal.infra.persistence.mapper.GameMediaEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGameMediaRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Optional<GameMedia> findById(GameMediaId gameMediaId) {
        return gameMediaRepository.findById(gameMediaId.value())
                .map(mapper::toDomain);
    }

    @Override
    public Set<GameMedia> findAllByGameId(GameId gameId) {
        return gameMediaRepository.findAllByGameId(gameId.value())
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteById(GameMediaId gameMediaId) {
        gameMediaRepository.deleteById(gameMediaId.value());
    }
}
