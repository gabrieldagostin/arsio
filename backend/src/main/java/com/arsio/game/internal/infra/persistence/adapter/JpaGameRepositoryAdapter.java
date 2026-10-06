package com.arsio.game.internal.infra.persistence.adapter;

import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.GameStatus;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.persistence.entity.GameEntity;
import com.arsio.game.internal.infra.persistence.mapper.GameEntityMapper;
import com.arsio.game.internal.infra.persistence.repository.SpringDataGameRepository;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaGameRepositoryAdapter implements GameRepository {

    private final GameEntityMapper mapper;
    private final PaginationMapper paginationMapper;
    private final SpringDataGameRepository games;

    @Override
    public void save(Game game) {
        GameEntity entity = mapper.toEntity(game);
        games.save(entity);
    }

    @Override
    public Optional<Game> findById(GameId gameId) {
        return games.findById(gameId.value())
                .map(mapper::toDomain);
    }

    @Override
    public PageResult<Game> findAll(Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<GameEntity> result = games.findAllByStatus(GameStatus.PUBLISHED, pageable);

        List<Game> gameList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                gameList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    @Override
    public PageResult<Game> findAll(String search, Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<GameEntity> result;

        if (search == null || search.isBlank()) {
            result = games.findAllByStatus(GameStatus.PUBLISHED, pageable);
        } else {
            result = games.findByTitleContainingIgnoreCaseAndStatus(search.trim(), GameStatus.PUBLISHED, pageable);
        }

        List<Game> gameList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                gameList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    @Override
    public PageResult<Game> findAllByFeaturedTrue(Pagination pagination) {

        Pageable pageable = paginationMapper.toPageable(pagination);

        Page<GameEntity> result = games.findAllByFeaturedTrue(pageable);

        List<Game> gameList = result.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                gameList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}
