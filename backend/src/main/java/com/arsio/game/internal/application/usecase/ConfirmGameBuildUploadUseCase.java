package com.arsio.game.internal.application.usecase;

import com.arsio.game.internal.application.port.output.GameBuildStorage;
import com.arsio.game.internal.domain.model.GameBuild;
import com.arsio.game.internal.domain.repository.GameBuildRepository;
import com.arsio.game.internal.domain.valueobject.BuildStatus;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.response.GameBuildUploadCompletionResponse;
import com.arsio.shared.storage.ConfirmFileUploadCommand;
import com.arsio.shared.storage.ObjectMetadata;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ConfirmGameBuildUploadUseCase {

    private final GameBuildRepository gameBuilds;
    private final GameBuildStorage buildStorage;

    public ConfirmGameBuildUploadUseCase(GameBuildRepository gameBuilds, GameBuildStorage buildStorage) {
        this.gameBuilds = gameBuilds;
        this.buildStorage = buildStorage;
    }

    public GameBuildUploadCompletionResponse execute(UUID value, ConfirmFileUploadCommand command) {

        GameId gameId = new GameId(value);

        ObjectMetadata objectMetadata = buildStorage.getObjectMetadata(command.objectKey().value());

        GameBuild gameBuild = gameBuilds.findByGameId(gameId)
                .orElse(
                        GameBuild.create(
                                gameId,
                                command.objectKey(),
                                objectMetadata.size(),
                                BuildStatus.READY
                        )
                );

        gameBuild.updateObjectKey(command.objectKey());

        gameBuilds.save(gameBuild);

        return new GameBuildUploadCompletionResponse(
                gameBuild.getId().value(),
                gameBuild.getFileSize().value(),
                gameBuild.getStatus().name()
        );
    }
}
