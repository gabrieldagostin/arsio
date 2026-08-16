package com.arsio.user.internal.infra.controller.mapper;

import com.arsio.user.internal.application.dto.ConfirmAvatarUploadCommand;
import com.arsio.user.internal.application.dto.UpdatePasswordCommand;
import com.arsio.user.internal.application.dto.UpdateUsernameCommand;
import com.arsio.user.internal.application.dto.UploadAvatarUrlCommand;
import com.arsio.user.internal.infra.controller.dto.request.ConfirmAvatarUploadRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdatePasswordRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateUsernameRequest;
import com.arsio.user.internal.infra.controller.dto.request.UploadAvatarUrlRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserControllerMapper {

    UpdateUsernameCommand toUpdateUsernameCommand(UpdateUsernameRequest request);

    UpdatePasswordCommand toUpdatePasswordCommand(UpdatePasswordRequest request);

    UploadAvatarUrlCommand toUploadAvatarUrlCommand(UploadAvatarUrlRequest request);

    ConfirmAvatarUploadCommand toConfirmAvatarUploadCommand(ConfirmAvatarUploadRequest request);
}
