package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.command.DeleteTagFromGameCommand;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteTagFromGameUseCase {

    private final GameRepository games;

    public DeleteTagFromGameUseCase(GameRepository games) {
        this.games = games;
    }

    public void execute(DeleteTagFromGameCommand command) {

        Game game = games.findById(command.gameId())
                .orElseThrow(GameNotFoundException::new);

        game.getTags().remove(command.tagId());

        games.save(game);
    }
}
