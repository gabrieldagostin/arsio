package com.arsio.game.internal.infra.Controller.mapper;

import com.arsio.config.mapper.CentralMapperConfig;
import com.arsio.shared.storage.ConfirmFileUploadCommand;
import com.arsio.shared.storage.ConfirmFileUploadRequest;
import com.arsio.shared.storage.UploadFileUrlCommand;
import com.arsio.shared.storage.UploadFileUrlRequest;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface GameMediaControllerMapper {

    UploadFileUrlCommand toUploadFileUrlCommand(UploadFileUrlRequest request);

    ConfirmFileUploadCommand toConfirmFileUploadCommand(ConfirmFileUploadRequest request);
}
