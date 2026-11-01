package com.arsio.game.internal.application.facade;

import com.arsio.game.api.dto.GetDeveloperGamesView;
import com.arsio.game.api.facade.GameFacade;
import com.arsio.game.internal.application.usecase.GetDeveloperGamesUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GameFacadeImpl implements GameFacade {

    private final GetDeveloperGamesUseCase getDeveloperGamesUseCase;

    public GameFacadeImpl(GetDeveloperGamesUseCase getDeveloperGamesUseCase) {
        this.getDeveloperGamesUseCase = getDeveloperGamesUseCase;
    }

    @Override
    public List<GetDeveloperGamesView> findByDeveloperId(UUID developerId) {
        return getDeveloperGamesUseCase.execute(developerId);
    }
}
