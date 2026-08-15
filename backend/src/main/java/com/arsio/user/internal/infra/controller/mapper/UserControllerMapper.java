package com.arsio.user.internal.infra.controller.mapper;

import com.arsio.user.internal.application.dto.UpdatePasswordCommand;
import com.arsio.user.internal.application.dto.UpdateUsernameCommand;
import com.arsio.user.internal.infra.controller.dto.request.UpdatePasswordRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateUsernameRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserControllerMapper {

    UpdateUsernameCommand toUpdateUsernameCommand(UpdateUsernameRequest request);

    UpdatePasswordCommand toUpdatePasswordCommand(UpdatePasswordRequest request);
}
