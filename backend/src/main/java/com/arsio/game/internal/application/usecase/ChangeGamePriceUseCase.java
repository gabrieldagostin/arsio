package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameNotFoundException;
import com.arsio.game.internal.application.command.ChangeGamePriceCommand;
import com.arsio.game.internal.domain.model.Game;
import com.arsio.game.internal.domain.repository.GameRepository;
import com.arsio.game.internal.infra.Controller.dto.response.ChangeGamePriceResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ChangeGamePriceUseCase {

    private final GameRepository games;

    public ChangeGamePriceUseCase(GameRepository games) {
        this.games = games;
    }

    public ChangeGamePriceResponse execute(ChangeGamePriceCommand command) {

        Game game = games.findById(command.gameId())
                .orElseThrow(GameNotFoundException::new);

        game.changePrice(command.newPrice());

        games.save(game);

        return new ChangeGamePriceResponse(
                game.getId().value(),
                game.getBasePrice().amount()
        );
    }
}
