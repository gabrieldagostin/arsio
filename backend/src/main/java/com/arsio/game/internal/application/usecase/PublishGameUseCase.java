package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.domain.exception.InvalidBuildStatusException;
import com.arsio.game.internal.domain.exception.InvalidGameStatusException;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.GameBuild;
import com.arsio.game.internal.domain.model.GameStatus;
import com.arsio.game.internal.domain.repository.GameBuildRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.BuildStatus;
import com.arsio.game.internal.domain.valueobject.GameId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class PublishGameUseCase {

    private final GameRepository games;
    private final GameBuildRepository gameBuilds;

    public PublishGameUseCase(GameRepository games, GameBuildRepository gameBuilds) {
        this.games = games;
        this.gameBuilds = gameBuilds;
    }

    public void execute(UUID value) {

        GameId gameId = new GameId(value);

        Game game = games.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        GameBuild gameBuild = gameBuilds.findByGameId(gameId)
                .orElseThrow(GameNotFoundException::new);

        if (game.getStatus() != GameStatus.DRAFT)
            throw new InvalidGameStatusException();

        if (gameBuild.getStatus() != BuildStatus.READY)
            throw new InvalidBuildStatusException();

        gameBuild.updateStatus(BuildStatus.PUBLISHED);

        gameBuilds.save(gameBuild);

        game.updateStatus(GameStatus.PENDING);

        games.save(game);
    }
}
