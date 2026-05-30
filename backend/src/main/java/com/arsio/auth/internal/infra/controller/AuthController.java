package com.arsio.auth.internal.infra.controller;

import com.arsio.auth.internal.application.port.input.CreateUserUseCase;
import com.arsio.auth.internal.application.port.input.LoginUserUseCase;
import com.arsio.auth.internal.infra.controller.dto.*;
import com.arsio.auth.internal.infra.mapper.AuthRequestMapper;
import com.arsio.config.security.SecurityConfigurations;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConfigurations.SECURITY)
@RequestMapping("/auth")
public class AuthController {

    private final AuthRequestMapper mapper;
    private final CreateUserUseCase createUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterUserRequest request, UriComponentsBuilder builder) {

        CreateUserCommand command = mapper.toCreateUserCommand(request);

        AuthenticationResponse result = createUserUseCase.execute(command);

        URI uri = builder.path("/users/{id}")
                .buildAndExpand(result).toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginUserRequest request, UriComponentsBuilder builder) {

        LoginUserComand command = mapper.toLoginUserCommand(request);

        AuthenticationResponse result = loginUserUseCase.execute(command);

        return ResponseEntity.ok(result);
    }


}
