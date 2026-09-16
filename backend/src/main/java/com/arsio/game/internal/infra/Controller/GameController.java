package com.arsio.game.internal.infra.Controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.game.internal.application.command.CreateGameCommand;
import com.arsio.game.internal.application.usecase.CreateGameUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGameRequest;
import com.arsio.game.internal.infra.Controller.dto.response.CreateGameResponse;
import com.arsio.game.internal.infra.Controller.mapper.GameControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameControllerMapper mapper;
    private final CreateGameUseCase registerGameUseCase;

    @PostMapping
    @PreAuthorize("hasRole('DEV')")
    public ResponseEntity<CreateGameResponse> create(@RequestBody @Valid CreateGameRequest request, UriComponentsBuilder builder) {

        CreateGameCommand command = mapper.toCreateGameCommand(request);

        CreateGameResponse response =
                registerGameUseCase.execute(SecurityUtils.getCurrentUserId(), command);

        URI uri = builder.path("/games/{id}")
                .buildAndExpand(response).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
