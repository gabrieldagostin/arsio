package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.AddGameTrailerCommand;
import com.arsio.game.internal.application.service.GameMediaUploadPreparationService;
import com.arsio.game.internal.application.usecase.*;
import com.arsio.game.internal.infra.Controller.dto.request.AddGameTrailerRequest;
import com.arsio.game.internal.infra.Controller.dto.response.*;
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
@RequestMapping("/api/v1/game-media")
public class GameMediaController {

    private final GameMediaControllerMapper mapper;
    private final GameMediaUploadPreparationService gameMediaUploadPreparationService;
    private final ConfirmGameThumbnailUploadUseCase confirmGameThumbnailUploadUseCase;
    private final DeleteThumbnailUseCase deleteThumbnailUseCase;
    private final ConfirmGameBannerUploadUseCase confirmGameBannerUploadUseCase;
    private final DeleteBannerUseCase deleteBannerUseCase;
    private final ConfirmGameScreenshotUploadUseCase confirmGameScreenshotUploadUseCase;
    private final DeleteScreenshotUseCase deleteScreenshotUseCase;
    private final AddGameTrailerUseCase addGameTrailerUseCase;
    private final DeleteTrailerUseCase deleteTrailerUseCase;
    private final GetGameMediaUseCase getGameMediaUseCase;

    @PostMapping("/{gameId}/thumbnails/upload-url")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UploadFileUrlResponse> generateThumbnailUploadUrl(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid UploadFileUrlRequest request
    ) {

        UploadFileUrlCommand command = mapper.toUploadFileUrlCommand(request);

        UploadFileUrlResponse response =
                gameMediaUploadPreparationService.execute(gameId, command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}/thumbnails/confirm")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetThumbnailResponse> confirmThumbnailUpload(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid ConfirmFileUploadRequest request
    ) {

        ConfirmFileUploadCommand command = mapper.toConfirmFileUploadCommand(request);

        GetThumbnailResponse response =
                confirmGameThumbnailUploadUseCase.execute(gameId, command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/thumbnails/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteThumbnail(@PathVariable(name = "gameId") UUID gameId) {

        deleteThumbnailUseCase.execute(gameId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gameId}/banners/upload-url")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UploadFileUrlResponse> generateBannerUploadUrl(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid UploadFileUrlRequest request
    ) {

        UploadFileUrlCommand command = mapper.toUploadFileUrlCommand(request);

        UploadFileUrlResponse response =
                gameMediaUploadPreparationService.execute(gameId, command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}/banners/confirm")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetBannerResponse> confirmBannerUpload(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid ConfirmFileUploadRequest request
    ) {

        ConfirmFileUploadCommand command = mapper.toConfirmFileUploadCommand(request);

        GetBannerResponse response =
                confirmGameBannerUploadUseCase.execute(gameId, command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/banners/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteBanner(@PathVariable(name = "gameId") UUID gameId) {

        deleteBannerUseCase.execute(gameId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gameId}/screenshots/upload-url")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UploadFileUrlResponse> generateScreenshotUploadUrl(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid UploadFileUrlRequest request
    ) {

        UploadFileUrlCommand command = mapper.toUploadFileUrlCommand(request);

        UploadFileUrlResponse response =
                gameMediaUploadPreparationService.execute(gameId, command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}/screenshots/confirm")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetScreenshotResponse> confirmScreenshotUpload(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid ConfirmFileUploadRequest request
    ) {

        ConfirmFileUploadCommand command = mapper.toConfirmFileUploadCommand(request);

        GetScreenshotResponse response =
                confirmGameScreenshotUploadUseCase.execute(gameId, command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/screenshots/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteScreenshot(@PathVariable(name = "gameId") UUID gameId) {

        deleteScreenshotUseCase.execute(gameId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gameId}/trailers")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetTrailerResponse> addTrailer(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid AddGameTrailerRequest request
    ) {

        AddGameTrailerCommand command = mapper.toAddGameTrailerCommand(gameId, request);

        GetTrailerResponse response = addGameTrailerUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/trailers/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteTrailer(@PathVariable(name = "gameId") UUID gameId) {

        deleteTrailerUseCase.execute(gameId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{gameId}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetGameMediaResponse> getGameMedia(@PathVariable(name = "gameId") UUID gameId) {

        GetGameMediaResponse response = getGameMediaUseCase.execute(gameId);

        return ResponseEntity.ok(response);
    }
}
