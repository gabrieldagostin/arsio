package com.arsio.developer.internal.infra.controller;

import com.arsio.developer.internal.application.command.ApplyGameDiscountCommand;
import com.arsio.developer.internal.application.command.UpdateGameDiscountCommand;
import com.arsio.developer.internal.application.usecase.*;
import com.arsio.developer.internal.infra.controller.dto.request.ApplyGameDiscountRequest;
import com.arsio.developer.internal.infra.controller.dto.request.UpdateGameDiscountRequest;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperGameResponse;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperPageResponse;
import com.arsio.developer.internal.infra.controller.dto.response.GetDiscountedGameResponse;
import com.arsio.developer.internal.infra.controller.dto.response.UpdateGameDiscountResponse;
import com.arsio.developer.internal.infra.controller.mapper.DeveloperControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperControllerMapper mapper;
    private final GetDeveloperPageUseCase getDeveloperPageUseCase;
    private final GetDeveloperGamesUseCase getDeveloperGamesUseCase;
    private final ApplyDeveloperDiscountUseCase applyDeveloperDiscountUseCase;
    private final GetDeveloperDiscountedGamesUseCase getDeveloperDiscountedGamesUseCase;
    private final GetScheduledDiscountedGamesUseCase getScheduledDiscountedGamesUseCase;
    private final UpdateDeveloperDiscountUseCase updateDeveloperDiscountUseCase;
    private final DeleteDeveloperDiscountUseCase deleteDeveloperDiscountUseCase;

    @GetMapping("/{developerId}")
    @PreAuthorize("HasRole('User')")
    public ResponseEntity<GetDeveloperPageResponse> getDeveloperPage(
            @PathVariable(name = "developerId") UUID developerId) {

        GetDeveloperPageResponse response = getDeveloperPageUseCase.execute(developerId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{developerId}/games")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<List<GetDeveloperGameResponse>> getDeveloperGames(
            @PathVariable(name = "developerId") UUID developerId) {

        List<GetDeveloperGameResponse> responses = getDeveloperGamesUseCase.execute(developerId);

        return ResponseEntity.ok(responses);
    }

    @PostMapping("/discounts/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GetDiscountedGameResponse> applyDiscount(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid ApplyGameDiscountRequest request) {

        ApplyGameDiscountCommand command = mapper.toApplyGameDiscountCommand(gameId, request);

        GetDiscountedGameResponse response = applyDeveloperDiscountUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{developerId}/discounts")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<List<GetDiscountedGameResponse>> getDiscountedGames(
            @PathVariable(name = "developerId") UUID developerId) {

        List<GetDiscountedGameResponse> responses = getDeveloperDiscountedGamesUseCase.execute(developerId);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{developerId}/programmed/discounts")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<List<GetDiscountedGameResponse>> getScheduledDiscountedGames(
            @PathVariable(name = "developerId") UUID developerId) {

        List<GetDiscountedGameResponse> responses = getScheduledDiscountedGamesUseCase.execute(developerId);

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/discounts/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UpdateGameDiscountResponse> updateDiscount(
        @PathVariable(name = "gameId") UUID gameId,
        @RequestBody @Valid UpdateGameDiscountRequest request) {

        UpdateGameDiscountCommand command = mapper.toUpdateGameDiscountCommand(gameId, request);

        UpdateGameDiscountResponse response = updateDeveloperDiscountUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/discounts/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteDiscount(@PathVariable(name = "gameId") UUID gameId) {

        deleteDeveloperDiscountUseCase.execute(gameId);

        return ResponseEntity.noContent().build();
    }
}
