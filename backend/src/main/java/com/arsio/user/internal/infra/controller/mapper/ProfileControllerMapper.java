package com.arsio.user.internal.infra.controller.mapper;

import com.arsio.user.internal.application.dto.ConfirmFileUploadCommand;
import com.arsio.user.internal.application.dto.UpdateProfileBioCommand;
import com.arsio.user.internal.application.dto.UploadFileUrlCommand;
import com.arsio.user.internal.infra.controller.dto.request.ConfirmFileUploadRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateProfileBioRequest;
import com.arsio.user.internal.infra.controller.dto.request.UploadFileUrlRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface ProfileControllerMapper {

    UploadFileUrlCommand toUploadFileUrlCommand(UploadFileUrlRequest request);

    ConfirmFileUploadCommand toConfirmFileUploadCommand(ConfirmFileUploadRequest request);

    UpdateProfileBioCommand toUpdateProfileBioCommand(UpdateProfileBioRequest request);
}
