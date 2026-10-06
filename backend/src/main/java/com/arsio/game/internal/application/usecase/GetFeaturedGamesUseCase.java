package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.infra.Controller.dto.response.ListGamesResponse;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetFeaturedGamesUseCase {

    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage gameMediaStorage;

    public GetFeaturedGamesUseCase(GameRepository games, GameMediaRepository gameMediaRepository, GameMediaStorage gameMediaStorage) {
        this.games = games;
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaStorage = gameMediaStorage;
    }

    public PageResult<ListGamesResponse> execute(Pagination pagination) {


        return null;
    }
}
