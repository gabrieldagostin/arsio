package com.arsio.game.internal.application.usecase;

import com.arsio.game.api.exception.GameMediaNotFoundException;
import com.arsio.game.internal.application.port.output.GameMediaStorage;
import com.arsio.game.internal.domain.model.GameMedia;
import com.arsio.game.internal.domain.repository.GameMediaRepository;
import com.arsio.game.internal.domain.valueobject.GameMediaId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DeleteThumbnailUseCase {

    private final GameMediaRepository gameMediaRepository;
    private final GameMediaStorage fileStorage;

    public DeleteThumbnailUseCase(GameMediaRepository gameMediaRepository, GameMediaStorage fileStorage) {
        this.gameMediaRepository = gameMediaRepository;
        this.fileStorage = fileStorage;
    }

    public void execute(UUID id) {

        GameMediaId imageId = new GameMediaId(id);

        GameMedia gameMedia = gameMediaRepository.findById(imageId)
                .orElseThrow(GameMediaNotFoundException::new);

        fileStorage.delete(gameMedia.getObjectKey().value());

        gameMediaRepository.deleteById(imageId);
    }
}
