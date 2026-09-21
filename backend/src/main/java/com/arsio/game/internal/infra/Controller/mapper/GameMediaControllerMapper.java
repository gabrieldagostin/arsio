package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.DeleteScreenshotCommand;
import com.arsio.game.internal.domain.valueobject.GameMediaId;
import com.arsio.shared.storage.ConfirmFileUploadCommand;
import com.arsio.shared.storage.ConfirmFileUploadRequest;
import com.arsio.shared.storage.UploadFileUrlCommand;
import com.arsio.shared.storage.UploadFileUrlRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public interface GameMediaControllerMapper {

    UploadFileUrlCommand toUploadFileUrlCommand(UploadFileUrlRequest request);

    ConfirmFileUploadCommand toConfirmFileUploadCommand(ConfirmFileUploadRequest request);

    DeleteScreenshotCommand toDeleteScreenshotCommand(UUID imageId);

    default GameMediaId toGameMediaId(UUID value) {
        return new GameMediaId(value);
    }
}
