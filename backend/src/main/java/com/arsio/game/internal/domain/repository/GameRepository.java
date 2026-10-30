package com.arsio.game.internal.domain.repository;

import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.ListGamesResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

public interface GameRepository {

    void save(Game game);

    Optional<Game> findById(GameId gameId);

    PageResult<Game> findAll(Pagination pagination);

    PageResult<Game> findAll(String search, Pagination pagination);

    PageResult<Game> findAllByFeaturedTrue(Pagination pagination);

    PageResult<Game> findRecentlyReleases(Pagination pagination, LocalDate oneWeekAgo, LocalDate now);

    PageResult<Game> findDiscountedGames(Pagination pagination);
}
