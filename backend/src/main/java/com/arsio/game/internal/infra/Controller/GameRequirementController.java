package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.CreateGameRequirementCommand;
import com.arsio.game.internal.application.usecase.CreateGameRequirementUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGameRequirementRequest;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameRequirementResponse;
import com.arsio.game.internal.infra.Controller.mapper.GameRequirementControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-requirements")
public class GameRequirementController {

    private final GameRequirementControllerMapper mapper;
    private final CreateGameRequirementUseCase createGameRequirementUseCase;

    @PostMapping
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetGameRequirementResponse> create(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid CreateGameRequirementRequest request) {

        CreateGameRequirementCommand command = mapper.toCreateGameRequirementCommand(gameId, request);

        GetGameRequirementResponse response = createGameRequirementUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
