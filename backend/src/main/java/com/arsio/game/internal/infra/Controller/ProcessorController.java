package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.CreateProcessorCommand;
import com.arsio.game.internal.application.command.UpdateProcessorCommand;
import com.arsio.game.internal.application.usecase.CreateProcessorUseCase;
import com.arsio.game.internal.application.usecase.ListProcessorsUseCase;
import com.arsio.game.internal.application.usecase.UpdateProcessorUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.CreateProcessorRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateProcessorRequest;
import com.arsio.game.internal.infra.Controller.dto.response.GetProcessorResponse;
import com.arsio.game.internal.infra.Controller.mapper.ProcessorControllerMapper;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-requirements/processors")
public class ProcessorController {

    private final ProcessorControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final ListProcessorsUseCase listProcessorsUseCase;
    private final CreateProcessorUseCase createProcessorUseCase;
    private final UpdateProcessorUseCase updateProcessorUseCase;

    @GetMapping
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetProcessorResponse>> findAll(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 10,
                    sort = "model",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetProcessorResponse> response =
                listProcessorsUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetProcessorResponse> create(@RequestBody @Valid CreateProcessorRequest request) {

        CreateProcessorCommand command = mapper.toCreateProcessorCommand(request);

        GetProcessorResponse response =
                createProcessorUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{processorId}")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetProcessorResponse> update(
            @PathVariable(name = "processorId") UUID processorId,
            @RequestBody @Valid UpdateProcessorRequest request) {

        UpdateProcessorCommand command = mapper.toUpdateProcessorCommand(processorId, request);

        GetProcessorResponse response =
                updateProcessorUseCase.execute(command);

        return ResponseEntity.ok(response);
    }
}
