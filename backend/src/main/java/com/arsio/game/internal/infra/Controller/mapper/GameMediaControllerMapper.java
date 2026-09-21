package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.game.internal.application.command.AddGameTrailerCommand;
import com.arsio.game.internal.domain.valueobject.GameId;
import com.arsio.game.internal.infra.Controller.dto.request.AddGameTrailerRequest;
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

    AddGameTrailerCommand toAddGameTrailerCommand(UUID gameId, AddGameTrailerRequest request);

    default GameId toGameId(UUID value) {
        return new GameId(value);
    }
}
