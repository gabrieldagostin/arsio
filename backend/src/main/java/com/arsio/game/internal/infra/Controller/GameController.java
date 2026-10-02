package com.arsio.game.internal.infra.Controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.game.internal.application.command.CreateGameCommand;
import com.arsio.game.internal.application.command.UpdateGameCommand;
import com.arsio.game.internal.application.usecase.CreateGameUseCase;
import com.arsio.game.internal.application.usecase.GetGameUseCase;
import com.arsio.game.internal.application.usecase.ListGamesUseCase;
import com.arsio.game.internal.application.usecase.UpdateGameUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGameRequest;
import com.arsio.game.internal.infra.Controller.dto.response.CreateGameResponse;
import com.arsio.game.internal.infra.Controller.dto.response.GetGameResponse;
import com.arsio.game.internal.infra.Controller.dto.response.ListGamesResponse;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateGameRequest;
import com.arsio.game.internal.infra.Controller.dto.response.UpdateGameResponse;
import com.arsio.game.internal.infra.Controller.mapper.GameControllerMapper;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final CreateGameUseCase registerGameUseCase;
    private final GetGameUseCase getGameUseCase;
    private final ListGamesUseCase listGamesUseCase;
    private final UpdateGameUseCase updateGameUseCase;

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

    @GetMapping("/{gameId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GetGameResponse> get(@PathVariable(name = "gameId") UUID gameId) {

        GetGameResponse response = getGameUseCase.execute(gameId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<PageResult<ListGamesResponse>> findAll(
            @PageableDefault(size = 20)Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<ListGamesResponse> responses = listGamesUseCase.execute(pagination);

        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/gameId")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UpdateGameResponse> update(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid UpdateGameRequest request) {

        UpdateGameCommand command = mapper.toUpdateGameCommand(gameId, request);
        
        UpdateGameResponse response = updateGameUseCase.execute(command);

        return ResponseEntity.ok(response);
    }
}
