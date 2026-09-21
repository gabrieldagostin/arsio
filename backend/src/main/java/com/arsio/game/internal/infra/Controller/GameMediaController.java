package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.service.GameMediaUploadPreparationService;
import com.arsio.game.internal.application.usecase.*;
import com.arsio.game.internal.infra.Controller.dto.response.GetBannerResponse;
import com.arsio.game.internal.infra.Controller.dto.response.GetScreenshotResponse;
import com.arsio.game.internal.infra.Controller.dto.response.GetThumbnailResponse;
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
    private final ConfirmThumbnailUploadUseCase confirmThumbnailUploadUseCase;
    private final DeleteThumbnailUseCase deleteThumbnailUseCase;
    private final ConfirmBannerUploadUseCase confirmBannerUploadUseCase;
    private final ConfirmScreenshotUploadUseCase confirmScreenshotUploadUseCase;
    private final DeleteScreenshotUseCase deleteScreenshotUseCase;

    @PostMapping("/{id}/thumbnails/upload-url")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UploadFileUrlResponse> generateThumbnailUploadUrl(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid UploadFileUrlRequest request
    ) {

        UploadFileUrlCommand command = mapper.toUploadFileUrlCommand(request);

        UploadFileUrlResponse response =
                gameMediaUploadPreparationService.execute(id, command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/thumbnails/confirm")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetThumbnailResponse> confirmThumbnailUpload(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid ConfirmFileUploadRequest request
    ) {

        ConfirmFileUploadCommand command = mapper.toConfirmFileUploadCommand(request);

        GetThumbnailResponse response =
                confirmThumbnailUploadUseCase.execute(id, command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/banners/upload-url")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UploadFileUrlResponse> generateBannerUploadUrl(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid UploadFileUrlRequest request
    ) {

        UploadFileUrlCommand command = mapper.toUploadFileUrlCommand(request);

        UploadFileUrlResponse response =
                gameMediaUploadPreparationService.execute(id, command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/banners/confirm")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetBannerResponse> confirmBannerUpload(
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid ConfirmFileUploadRequest request
    ) {

        ConfirmFileUploadCommand command = mapper.toConfirmFileUploadCommand(request);

        GetBannerResponse response =
                confirmBannerUploadUseCase.execute(id, command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/thumbnails/{imageId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteThumbnail(@PathVariable(name = "imageId") UUID imageId) {

        deleteThumbnailUseCase.execute(imageId);

        return ResponseEntity.noContent().build();
    }

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

    @PostMapping("/{id}/screenshots/confirm")
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

    @DeleteMapping("/screenshots/{imageId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteScreenshot(@PathVariable(name = "imageId") UUID imageId) {

        deleteScreenshotUseCase.execute(imageId);

        return ResponseEntity.noContent().build();
    }
}
