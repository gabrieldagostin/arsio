package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.valueobject.GameMediaId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteTrailerUseCase {

    private final GameMediaRepository gameMediaRepository;

    public DeleteTrailerUseCase(GameMediaRepository gameMediaRepository) {
        this.gameMediaRepository = gameMediaRepository;
    }

    public void execute(UUID id) {

        GameMediaId gameMediaId = new GameMediaId(id);

        gameMediaRepository.deleteById(gameMediaId);
    }
}
