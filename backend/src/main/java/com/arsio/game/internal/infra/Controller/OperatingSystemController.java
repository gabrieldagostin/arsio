package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.CreateOperatingSystemCommand;
import com.arsio.game.internal.application.command.UpdateOperatingSystemCommand;
import com.arsio.game.internal.application.usecase.CreateOperatingSystemUseCase;
import com.arsio.game.internal.application.usecase.ListOperatingSystemsUseCase;
import com.arsio.game.internal.application.usecase.UpdateOperatingSystemUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.CreateOperatingSystemRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateOperatingSystemRequest;
import com.arsio.game.internal.infra.Controller.dto.response.GetOperatingSystemResponse;
import com.arsio.game.internal.infra.Controller.mapper.OperatingSystemControllerMapper;
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
@RequestMapping("/game-requirements/operating-systems")
public class OperatingSystemController {

    private final OperatingSystemControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final ListOperatingSystemsUseCase listOperatingSystemsUseCase;
    private final CreateOperatingSystemUseCase createOperatingSystemUseCase;
    private final UpdateOperatingSystemUseCase updateOperatingSystemUseCase;

    @GetMapping
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetOperatingSystemResponse>> getAll(
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

    @PostMapping
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetOperatingSystemResponse> create(@RequestBody @Valid CreateOperatingSystemRequest request) {

        CreateOperatingSystemCommand command = mapper.toCreateOperatingSystemCommand(request);

        GetOperatingSystemResponse response = createOperatingSystemUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{operatingSystemId}")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetOperatingSystemResponse> update(
            @RequestParam(name = "operatingSystemId") UUID operatingSystemId,
            @RequestBody @Valid UpdateOperatingSystemRequest request) {

        UpdateOperatingSystemCommand command = mapper.toUpdateOperatingSystemCommand(operatingSystemId, request);

        GetOperatingSystemResponse response = updateOperatingSystemUseCase.execute(command);

        return ResponseEntity.ok(response);
    }
}
