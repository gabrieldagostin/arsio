package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.*;
import com.arsio.game.internal.application.usecase.*;
import com.arsio.game.internal.infra.Controller.dto.request.*;
import com.arsio.game.internal.infra.Controller.dto.response.GetGpuResponse;
import com.arsio.game.internal.infra.Controller.dto.response.GetOperatingSystemResponse;
import com.arsio.game.internal.infra.Controller.dto.response.GetProcessorResponse;
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
@RequestMapping("/api/v1/admin/game-requirements")
public class AdminGameRequirementController {

    private final GameRequirementControllerMapper mapper;
    private final CreateGpuUseCase createGpuUseCase;
    private final UpdateGpuUseCase updateGpuUseCase;
    private final DeleteGpuUseCase deleteGpuUseCase;
    private final CreateOperatingSystemUseCase createOperatingSystemUseCase;
    private final UpdateOperatingSystemUseCase updateOperatingSystemUseCase;
    private final DeleteOperatingSystemUseCase deleteOperatingSystemUseCase;
    private final CreateProcessorUseCase createProcessorUseCase;
    private final UpdateProcessorUseCase updateProcessorUseCase;
    private final DeleteProcessorUseCase deleteProcessorUseCase;

    @PostMapping("/gpus")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetGpuResponse> createGpu(@RequestBody @Valid CreateGpuRequest request) {

        CreateGpuCommand command = mapper.toCreateGpuCommand(request);

        GetGpuResponse response = createGpuUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{gpuId}/gpus")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetGpuResponse> updateGpu(
            @PathVariable(name = "gpuId") UUID gpuId,
            @RequestBody @Valid UpdateGpuRequest request
    ) {

        UpdateGpuCommand command = mapper.toUpdateGpuCommand(gpuId, request);

        GetGpuResponse response = updateGpuUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{gpuId}/gpus")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Void>  deleteGpu(@PathVariable(name = "gpuId") UUID gpuId) {

        deleteGpuUseCase.execute(gpuId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/operating-systems")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetOperatingSystemResponse> createOperatingSystem(@RequestBody @Valid CreateOperatingSystemRequest request) {

        CreateOperatingSystemCommand command = mapper.toCreateOperatingSystemCommand(request);

        GetOperatingSystemResponse response = createOperatingSystemUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{operatingSystemId}/operating-systems")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetOperatingSystemResponse> updateOperatingSystem(
            @PathVariable(name = "operatingSystemId") UUID operatingSystemId,
            @RequestBody @Valid UpdateOperatingSystemRequest request) {

        UpdateOperatingSystemCommand command = mapper.toUpdateOperatingSystemCommand(operatingSystemId, request);

        GetOperatingSystemResponse response = updateOperatingSystemUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{operatingSystemId}/operating-systems")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Void> deleteOperatingSystem(@PathVariable(name = "operatingSystemId") UUID operatingSystemId) {

        deleteOperatingSystemUseCase.execute(operatingSystemId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/processors")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetProcessorResponse> createProcessor(@RequestBody @Valid CreateProcessorRequest request) {

        CreateProcessorCommand command = mapper.toCreateProcessorCommand(request);

        GetProcessorResponse response = createProcessorUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{processorId}/processors")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetProcessorResponse> updateProcessor(
            @PathVariable(name = "processorId") UUID processorId,
            @RequestBody @Valid UpdateProcessorRequest request) {

        UpdateProcessorCommand command = mapper.toUpdateProcessorCommand(processorId, request);

        GetProcessorResponse response = updateProcessorUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{processorId}/processors")
    @PreAuthorize("HasRole('ADMIN')")
    private ResponseEntity<Void> deleteProcessor(@PathVariable(name = "processorId") UUID processorId) {

        deleteProcessorUseCase.execute(processorId);

        return ResponseEntity.noContent().build();
    }
}
