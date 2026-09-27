package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.CreateGpuCommand;
import com.arsio.game.internal.application.command.UpdateGpuCommand;
import com.arsio.game.internal.application.usecase.CreateGpuUseCase;
import com.arsio.game.internal.application.usecase.DeleteGpuUseCase;
import com.arsio.game.internal.application.usecase.ListGpusUseCase;
import com.arsio.game.internal.application.usecase.UpdateGpuUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGpuRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateGpuRequest;
import com.arsio.game.internal.infra.Controller.dto.response.GetGpuResponse;
import com.arsio.game.internal.infra.Controller.mapper.GpuControllerMapper;
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
@RequestMapping("/game-requirements/gpus")
public class GpuController {

    private final GpuControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final ListGpusUseCase listGpusUseCase;
    private final CreateGpuUseCase createGpuUseCase;
    private final UpdateGpuUseCase updateGpuUseCase;
    private final DeleteGpuUseCase deleteGpuUseCase;

    @GetMapping
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetGpuResponse>> findAll(
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

    @PostMapping
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetGpuResponse> create(@RequestBody @Valid CreateGpuRequest request) {

        CreateGpuCommand command = mapper.toCreateGpuCommand(request);

        GetGpuResponse response = createGpuUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{gpuId}")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetGpuResponse> update(
            @RequestParam(name = "gpuId") UUID gpuId,
            @RequestBody @Valid UpdateGpuRequest request
    ) {

        UpdateGpuCommand command = mapper.toUpdateGpuCommand(gpuId, request);

        GetGpuResponse response = updateGpuUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{gpuId}")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Void>  delete(@RequestParam(name = "gpuId") UUID gpuId) {

        deleteGpuUseCase.execute(gpuId);

        return ResponseEntity.noContent().build();
    }
}
