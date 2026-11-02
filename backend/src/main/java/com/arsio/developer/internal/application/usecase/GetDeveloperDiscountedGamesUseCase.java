package com.arsio.developer.internal.application.usecase;

import com.arsio.developer.internal.domain.valueobject.DeveloperId;
import com.arsio.developer.internal.infra.controller.dto.response.GetDiscountedGameResponse;
import com.arsio.game.api.facade.GameFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GetDeveloperDiscountedGamesUseCase {

    private final GameFacade gameFacade;

    public GetDeveloperDiscountedGamesUseCase(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public List<GetDiscountedGameResponse> execute(UUID value) {

        DeveloperId developerId = new DeveloperId(value);

        return gameFacade.findDiscountedByDeveloperId(developerId.value())
                .stream()
                .map(discountedGame -> new GetDiscountedGameResponse(
                        discountedGame.gameId(),
                        discountedGame.title(),
                        discountedGame.discountedPrice(),
                        discountedGame.discountedAmount(),
                        discountedGame.thumbnailUrl()
                ))
                .toList();
    }
}
