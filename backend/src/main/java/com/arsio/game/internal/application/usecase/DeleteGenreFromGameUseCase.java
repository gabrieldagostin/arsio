package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.command.DeleteGenreFromGameCommand;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteGenreFromGameUseCase {

    private final GameRepository games;

    public DeleteGenreFromGameUseCase(GameRepository games) {
        this.games = games;
    }

    public void execute(DeleteGenreFromGameCommand command) {

        Game game = games.findById(command.gameId())
                .orElseThrow(GameNotFoundException::new);

        game.getGenres().remove(command.genreId());

        games.save(game);
    }
}
