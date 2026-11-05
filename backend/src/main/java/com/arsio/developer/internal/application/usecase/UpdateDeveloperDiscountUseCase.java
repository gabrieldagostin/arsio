package com.arsio.developer.internal.application.usecase;

import com.arsio.developer.internal.application.command.UpdateGameDiscountCommand;
import com.arsio.developer.internal.infra.controller.dto.response.UpdateGameDiscountResponse;
import com.arsio.game.api.exception.DiscountNotFoundException;
import com.arsio.game.api.facade.GameFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateDeveloperDiscountUseCase {

    private final GameFacade gameFacade;

    public UpdateDeveloperDiscountUseCase(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public UpdateGameDiscountResponse execute(UpdateGameDiscountCommand command) {

        return gameFacade.updateGameDiscount(
                command.gameId(),
                command.percentage(),
                command.startDate(),
                command.endDate()
        )
                .map(discountedGame -> new UpdateGameDiscountResponse(
                        discountedGame.discountedPrice(),
                        discountedGame.discountedAmount(),
                        discountedGame.startDate(),
                        discountedGame.endDate()
                ))
                .orElseThrow(DiscountNotFoundException::new);
    }
}
