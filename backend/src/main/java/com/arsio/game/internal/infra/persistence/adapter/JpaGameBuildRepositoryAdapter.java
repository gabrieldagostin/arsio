package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.GameBuild;
import com.arsio.game.internal.domain.repository.GameBuildRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.persistence.entity.GameBuildEntity;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.mapper.GameBuildEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGameBuildRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaGameBuildRepositoryAdapter implements GameBuildRepository {

    private final GameBuildEntityMapper mapper;
    private final SpringDataGameBuildRepository gameBuilds;
    private final EntityManager entityManager;

    @Override
    public Optional<GameBuild> findByGameId(GameId gameId) {
        return gameBuilds.findByGameId(gameId)
                .map(mapper::toDomain);
    }

    @Override
    public void save(GameBuild gameBuild) {

        GameEntity gameEntity = entityManager.getReference(
                GameEntity.class,
                gameBuild.getGameId()
        );

        GameBuildEntity gameBuildEntity = mapper.toEntity(gameBuild, gameEntity);

        gameBuilds.save(gameBuildEntity);
    }
}
