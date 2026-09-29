package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.service.GameMediaService;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameMediaResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GetGameMediaUseCase {

    private final GameMediaService gameMediaService;

    public GetGameMediaUseCase(GameMediaService gameMediaService) {
        this.gameMediaService = gameMediaService;
    }

    public GetGameMediaResponse execute(UUID value) {

        GameId gameId = new GameId(value);

        return gameMediaService.getByGameId(gameId);
    }
}
