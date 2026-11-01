package com.arsio.developer.internal.application.usecase;

import com.arsio.developer.internal.domain.valueobject.DeveloperId;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperGamesResponse;
import com.arsio.game.api.facade.GameFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GetDeveloperGamesUseCase {

    private final GameFacade gameFacade;

    public  GetDeveloperGamesUseCase(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public List<GetDeveloperGamesResponse> execute(UUID value) {

        DeveloperId developerId = new DeveloperId(value);

        List<GetDeveloperGamesResponse> developerGames = gameFacade.findByDeveloperId(developerId.value())
                .stream()
                .map(game -> new GetDeveloperGamesResponse(
                        game.gameId(),
                        game.title(),
                        game.basePrice(),
                        game.thumbnailUrl()
                ))
                .toList();

        return developerGames;
    }
}
