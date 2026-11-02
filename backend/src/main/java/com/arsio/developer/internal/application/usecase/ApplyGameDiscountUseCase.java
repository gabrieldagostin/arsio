package com.arsio.developer.internal.application.usecase;

import com.arsio.developer.internal.application.command.ApplyGameDiscountCommand;
import com.arsio.developer.internal.infra.controller.dto.response.GetDiscountedGameResponse;
import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.api.facade.GameFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ApplyGameDiscountUseCase {

    private final GameFacade gameFacade;

    public ApplyGameDiscountUseCase(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public GetDiscountedGameResponse execute(ApplyGameDiscountCommand command) {

        return gameFacade.applyGameDiscount(
                command.gameId(),
                command.percentage(),
                command.startDate(),
                command.endDate()
        )
                .map(discountedGame -> new GetDiscountedGameResponse(
                        discountedGame.gameId(),
                        discountedGame.title(),
                        discountedGame.discountedPrice(),
                        discountedGame.discountedAmount(),
                        discountedGame.thumbnailUrl()
                ))
                .orElseThrow(GameNotFoundException::new);
    }
}
