package com.arsio.auth.internal.infra.controller.mapper;

import com.arsio.auth.internal.application.dto.CreateUserCommand;
import com.arsio.auth.internal.application.dto.LoginUserComand;
import com.arsio.auth.internal.application.dto.LogoutCommand;
import com.arsio.auth.internal.application.dto.RefreshTokenCommand;
import com.arsio.auth.internal.infra.controller.dto.LoginUserRequest;
import com.arsio.auth.internal.infra.controller.dto.LogoutRequest;
import com.arsio.auth.internal.infra.controller.dto.RefreshTokenRequest;
import com.arsio.auth.internal.infra.controller.dto.RegisterUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface AuthControllerMapper {

     CreateUserCommand toCreateUserCommand(RegisterUserRequest request);

     LoginUserComand toLoginUserCommand(LoginUserRequest request);

     RefreshTokenCommand toRefreshTokenCommand(RefreshTokenRequest request);

     LogoutCommand toLogoutCommand(LogoutRequest request);
}
