package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.command.AddGameTrailerCommand;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.domain.valueobject.MediaRole;
import com.arsio.game.internal.domain.valueobject.MediaType;
import com.arsio.game.internal.infra.Controller.dto.response.GetTrailerResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AddGameTrailerUseCase {

    private final GameRepository games;
    private final GameMediaRepository gameMediaRepository;

    public AddGameTrailerUseCase(GameRepository games, GameMediaRepository gameMediaRepository) {
        this.games = games;
        this.gameMediaRepository = gameMediaRepository;
    }

    public GetTrailerResponse execute(AddGameTrailerCommand command) {

        Game game = games.findById(command.gameId())
                .orElseThrow(GameNotFoundException::new);

        GameMedia gameMedia = GameMedia.createVideo(
                game.getId(),
                MediaType.VIDEO,
                MediaRole.TRAILER,
                command.externalUrl()
        );

        gameMediaRepository.save(gameMedia);

        return new GetTrailerResponse(gameMedia.getExternalUrl().value());
    }
}
