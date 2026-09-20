package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.service.GameMediaUploadPreparationService;
import com.arsio.game.internal.application.usecase.ConfirmScreenshotUploadUseCase;
import com.arsio.game.internal.infra.Controller.dto.response.GetScreenshotResponse;
import com.arsio.game.internal.infra.Controller.mapper.GameMediaControllerMapper;
import com.arsio.shared.storage.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-media")
public class GameMediaController {

    private final GameMediaControllerMapper mapper;
    private final GameMediaUploadPreparationService gameMediaUploadPreparationService;
    private final ConfirmScreenshotUploadUseCase confirmScreenshotUploadUseCase;

    @PostMapping("/{id}/screenshots/upload-url")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UploadFileUrlResponse> generateScreenshotUploadUrl(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid UploadFileUrlRequest request
    ) {

        UploadFileUrlCommand command = mapper.toUploadFileUrlCommand(request);

        UploadFileUrlResponse response =
                gameMediaUploadPreparationService.execute(id, command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/screenshot/confirm")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetScreenshotResponse> confirmScreenshotUpload(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid ConfirmFileUploadRequest request
    ) {

        ConfirmFileUploadCommand command = mapper.toConfirmFileUploadCommand(request);

        GetScreenshotResponse response =
                confirmScreenshotUploadUseCase.execute(id, command);

        return ResponseEntity.ok(response);
    }
}
