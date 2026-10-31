package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.CreateGameRequirementCommand;
import com.arsio.game.internal.application.command.UpdateGameRequirementCommand;
import com.arsio.game.internal.application.usecase.*;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGameRequirementRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateGameRequirementRequest;
import com.arsio.game.internal.infra.Controller.dto.response.*;
import com.arsio.game.internal.infra.Controller.mapper.GameRequirementControllerMapper;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game-requirements")
public class GameRequirementController {

    private final GameRequirementControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final CreateGameRequirementUseCase createGameRequirementUseCase;
    private final GetGameRequirementsUseCase getGameRequirementsUseCase;
    private final UpdateGameRequirementsUseCase updateGameRequirementsUseCase;
    private final ListGpusUseCase listGpusUseCase;
    private final ListInactiveGpusUseCase listInactiveGpusUseCase;
    private final ListOperatingSystemsUseCase listOperatingSystemsUseCase;
    private final ListInactiveOperatingSystemsUseCase listInactiveOperatingSystemsUseCase;
    private final ListProcessorsUseCase listProcessorsUseCase;
    private final ListInactiveProcessorsUseCase listInactiveProcessorsUseCase;

    @PostMapping("/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetGameRequirementResponse> create(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid CreateGameRequirementRequest request) {

        CreateGameRequirementCommand command = mapper.toCreateGameRequirementCommand(gameId, request);

        GetGameRequirementResponse response = createGameRequirementUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{gameId}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetGameRequirementsResponse> get(@PathVariable(name = "gameId") UUID gameId) {

        GetGameRequirementsResponse response = getGameRequirementsUseCase.execute(gameId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetGameRequirementResponse> update(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid UpdateGameRequirementRequest request) {

        UpdateGameRequirementCommand command = mapper.toUpdateGameRequirementCommand(gameId, request);

        GetGameRequirementResponse response = updateGameRequirementsUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/gpus")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetGpuResponse>> findAllGpus(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 10,
                    sort = "model",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetGpuResponse> response = listGpusUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/inactive/gpus")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetGpuResponse>> findAllInactiveGpus(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 10,
                    sort = "model",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetGpuResponse> response = listInactiveGpusUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/operating-systems")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetOperatingSystemResponse>> findAllOperatingSystems(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 10,
                    sort = "name",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetOperatingSystemResponse> response =
                listOperatingSystemsUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/inactive/operating-systems")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetOperatingSystemResponse>> findAllInactiveOperatingSystems(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 10,
                    sort = "name",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetOperatingSystemResponse> response =
                listInactiveOperatingSystemsUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/processors")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetProcessorResponse>> findAllProcessors(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 10,
                    sort = "model",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetProcessorResponse> response = listProcessorsUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/inactive/processors")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetProcessorResponse>> findAllInactiveProcessors(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 10,
                    sort = "model",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetProcessorResponse> response = listInactiveProcessorsUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }
}
