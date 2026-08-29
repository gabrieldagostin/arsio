package com.arsio.user.internal.infra.controller.mapper;

import com.arsio.user.internal.application.command.UpdatePasswordCommand;
import com.arsio.user.internal.application.command.UpdateUsernameCommand;
import com.arsio.user.internal.domain.valueobject.PasswordHash;
import com.arsio.user.internal.domain.valueobject.Username;
import com.arsio.user.internal.infra.controller.dto.request.UpdatePasswordRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateUsernameRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface UserControllerMapper {

    UpdateUsernameCommand toUpdateUsernameCommand(UpdateUsernameRequest request);

    UpdatePasswordCommand toUpdatePasswordCommand(UpdatePasswordRequest request);

    default Username stringToUsername(String username){
        return new Username(username);
    }

    default PasswordHash stringToPassword(String password){
        return new PasswordHash(password);
    }
}
