package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameMediaNotFoundException;
import com.arsio.game.internal.application.command.DeleteScreenshotCommand;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteScreenshotUseCase {

    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage fileStorage;

    public DeleteScreenshotUseCase(GameMediaRepository gameMediaRepository, GameMediaStorage fileStorage) {
        this.gameMediaRepository = gameMediaRepository;
        this.fileStorage = fileStorage;
    }

    public void execute(DeleteScreenshotCommand command) {

        GameMedia gameMedia = gameMediaRepository.findById(command.imageId())
                .orElseThrow(GameMediaNotFoundException::new);

        fileStorage.delete(gameMedia.getObjectKey().value());

        gameMediaRepository.deleteById(command.imageId());
    }
}
