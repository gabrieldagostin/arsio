package com.arsio.game.internal.application.usecase;

import com.arsio.developer.api.facade.DeveloperFacade;
import com.arsio.game.internal.application.command.CreateGameCommand;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.infra.Controller.dto.response.CreateGameResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class CreateGameUseCase {

    private final GameRepository games;
    private final DeveloperFacade developerFacade;

    public CreateGameUseCase(GameRepository games, DeveloperFacade developerFacade) {
        this.games = games;
        this.developerFacade = developerFacade;
    }

    public CreateGameResponse execute(UUID developerId, CreateGameCommand command) {

        developerFacade.checkExists(developerId);

        Game game = Game.create(
                developerId,
                command.title(),
                command.description(),
                command.basePrice(),
                command.releaseDate()
        );

        games.save(game);

        return new CreateGameResponse(
                game.getId().value(),
                game.getDeveloperId(),
                game.getTitle().value(),
                game.getDescription().value(),
                game.getBasePrice().amount(),
                game.getStatus().name(),
                game.getReleaseDate()
        );
    }
}
