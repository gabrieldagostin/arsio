package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameMediaNotFoundException;
import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.ListGamesResponse;
import com.arsio.order.api.dto.GameSalesView;
import com.arsio.order.api.facade.OrderFacade;
import com.arsio.shared.pagination.Pagination;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetBestSellingGamesUseCase {

    private final OrderFacade orderFacade;
    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage gameMediaStorage;

    public GetBestSellingGamesUseCase(OrderFacade orderFacade, GameRepository games, GameMediaRepository gameMediaRepository, GameMediaStorage gameMediaStorage) {
        this.orderFacade = orderFacade;
        this.games = games;
        this.gameMediaRepository = gameMediaRepository;
        this.gameMediaStorage = gameMediaStorage;
    }

    public List<ListGamesResponse> execute(Pagination pagination) {

        List<GameSalesView> sales = orderFacade.getGameSales(pagination);

        return sales.stream()
                .map(gameSalesView -> {

                    Game game = games.findById(new GameId(gameSalesView.gameId()))
                            .orElseThrow(GameNotFoundException::new);

                    GameMedia gameMedia = gameMediaRepository.findByGameIdAndRoleThumbnail(
                            new GameId(gameSalesView.gameId()))
                            .orElseThrow(GameMediaNotFoundException::new);

                    String thumbnailUrl = gameMediaStorage.generatePresignedDownloadUrl(gameMedia.getObjectKey().value());

                    return new ListGamesResponse(
                            game.getId().value(),
                            game.getTitle().value(),
                            game.getBasePrice().amount(),
                            thumbnailUrl
                    );
                })
                .toList();
    }
}
