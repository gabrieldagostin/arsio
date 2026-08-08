package com.arsio.auth.internal.infra.controller.mapper;

import com.arsio.auth.internal.application.dto.*;
import com.arsio.auth.internal.infra.controller.dto.request.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
typeConversionPolicy = ReportingPolicy.ERROR)
public interface AuthControllerMapper {

     CreateUserCommand toCreateUserCommand(RegisterUserRequest request);

     LoginUserComand toLoginUserCommand(LoginUserRequest request);

     RefreshTokenCommand toRefreshTokenCommand(RefreshTokenRequest request);

     LogoutCommand toLogoutCommand(LogoutRequest request);

     ForgotPasswordCommand toForgotPasswordCommand(ForgotPasswordRequest request);

     ResetPasswordCommand toResetPasswordCommand(ResetPasswordRequest request);
}
