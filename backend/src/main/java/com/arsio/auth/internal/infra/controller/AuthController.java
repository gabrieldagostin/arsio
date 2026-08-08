package com.arsio.auth.internal.infra.controller;

import com.arsio.auth.internal.application.dto.*;
import com.arsio.auth.internal.application.service.*;
import com.arsio.auth.internal.infra.controller.dto.request.*;
import com.arsio.auth.internal.infra.controller.dto.response.AuthenticationResponse;
import com.arsio.auth.internal.infra.controller.dto.response.RefreshTokenResponse;
import com.arsio.auth.internal.infra.controller.mapper.AuthControllerMapper;
import com.arsio.config.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthControllerMapper mapper;
    private final RegisterUserService registerUserService;
    private final LoginUserService loginUserService;
    private final RefreshTokenService refreshTokenService;
    private final LogoutUserService logoutUserService;
    private final ForgotPasswordService forgotPasswordService;
    private final ResetPasswordService resetPasswordService;
    private final RevokeAllSessionsService revokeAllSessionsService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterUserRequest request, UriComponentsBuilder builder) {

        CreateUserCommand command = mapper.toCreateUserCommand(request);

        AuthenticationResponse response = registerUserService.execute(command);

        URI uri = builder.path("/users/{id}")
                .buildAndExpand(response).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginUserRequest request, UriComponentsBuilder builder) {

        LoginUserComand command = mapper.toLoginUserCommand(request);

        AuthenticationResponse response = loginUserService.execute(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {

        RefreshTokenCommand command = mapper.toRefreshTokenCommand(request);

        RefreshTokenResponse response = refreshTokenService.execute(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> logout(@RequestBody @Valid LogoutRequest request) {

        LogoutCommand command = mapper.toLogoutCommand(request);

        logoutUserService.execute(command);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {

        ForgotPasswordCommand command = mapper.toForgotPasswordCommand(request);

        forgotPasswordService.execute(command);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {

        ResetPasswordCommand command = mapper.toResetPasswordCommand(request);

        resetPasswordService.execute(command);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/sessions")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> revokeAllSessions() {

        revokeAllSessionsService.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.noContent().build();
    }
}
