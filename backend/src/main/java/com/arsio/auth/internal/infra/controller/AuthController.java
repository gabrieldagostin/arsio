package com.arsio.auth.internal.infra.controller;

import com.arsio.auth.internal.application.command.*;
import com.arsio.auth.internal.application.usecase.*;
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
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthControllerMapper mapper;
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUserUseCase logoutUserUseCase;
    private final ForgotPasswordUseCase forgotPasswordUseCase;
    private final ResetPasswordUseCase resetPasswordUseCase;
    private final RevokeAllSessionsUseCase revokeAllSessionsUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterUserRequest request, UriComponentsBuilder builder) {

        CreateUserCommand command = mapper.toCreateUserCommand(request);

        AuthenticationResponse response = registerUserUseCase.execute(command);

        URI uri = builder.path("/users/{id}")
                .buildAndExpand(response).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginUserRequest request, UriComponentsBuilder builder) {

        LoginUserCommand command = mapper.toLoginUserCommand(request);

        AuthenticationResponse response = loginUserUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {

        RefreshTokenCommand command = mapper.toRefreshTokenCommand(request);

        RefreshTokenResponse response = refreshTokenUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> logout(@RequestBody @Valid LogoutRequest request) {

        LogoutCommand command = mapper.toLogoutCommand(request);

        logoutUserUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {

        ForgotPasswordCommand command = mapper.toForgotPasswordCommand(request);

        forgotPasswordUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {

        ResetPasswordCommand command = mapper.toResetPasswordCommand(request);

        resetPasswordUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/sessions")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> revokeAllSessions() {

        revokeAllSessionsUseCase.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.noContent().build();
    }
}
