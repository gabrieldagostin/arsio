package com.arsio.developer.internal.application.usecase;

import com.arsio.game.api.facade.GameFacade;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteGameDiscountUseCase {

    private final GameFacade gameFacade;

    public DeleteGameDiscountUseCase(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    public void execute(UUID gameId) {
        gameFacade.deleteDiscount(gameId);
    }
}
