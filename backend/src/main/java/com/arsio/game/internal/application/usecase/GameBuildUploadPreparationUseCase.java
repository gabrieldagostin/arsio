package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.port.output.GameBuildStorage;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.shared.storage.UploadFileUrlCommand;
import com.arsio.shared.storage.UploadFileUrlResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class GameBuildUploadPreparationUseCase {

    private final GameBuildStorage fileStorage;

    public GameBuildUploadPreparationUseCase(GameBuildStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    public UploadFileUrlResponse execute(UUID value, UploadFileUrlCommand command) {

        GameId gameId = new GameId(value);

        String objectKey = String.format(
                "builds/%s/%s",
                gameId.value(),
                UUID.randomUUID()
        );

        String uploadUrl = fileStorage.generatePresignedUploadUrl(objectKey);

        return new UploadFileUrlResponse(
                uploadUrl,
                objectKey
        );
    }
}
