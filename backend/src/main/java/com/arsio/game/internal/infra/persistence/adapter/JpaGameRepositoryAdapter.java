package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.mapper.GameEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaGameRepositoryAdapter implements GameRepository {

    private final GameEntityMapper mapper;
    private final SpringDataGameRepository games;

    @Override
    public void save(Game game) {
        GameEntity entity = mapper.toEntity(game);
        games.save(entity);
    }
}
