package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.command.UpdateGameCommand;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.infra.Controller.dto.response.UpdateGameResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UpdateGameUseCase {

    private final GameRepository games;

    public UpdateGameUseCase(GameRepository games) {
        this.games = games;
    }

    public UpdateGameResponse execute(UpdateGameCommand command) {

        Game game = games.findById(command.gameId())
                .orElseThrow(GameNotFoundException::new);

        game.update(
                command.title(),
                command.description(),
                command.releaseDate()
        );

        games.save(game);

        return new UpdateGameResponse(
                game.getTitle().value(),
                game.getDescription().value(),
                game.getReleaseDate()
        );
    }
}
