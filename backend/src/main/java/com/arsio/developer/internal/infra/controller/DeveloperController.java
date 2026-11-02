package com.arsio.developer.internal.infra.controller;

import com.arsio.developer.internal.application.command.ApplyGameDiscountCommand;
import com.arsio.developer.internal.application.usecase.ApplyGameDiscountUseCase;
import com.arsio.developer.internal.application.usecase.GetDeveloperGamesUseCase;
import com.arsio.developer.internal.application.usecase.GetDeveloperPageUseCase;
import com.arsio.developer.internal.infra.controller.dto.request.ApplyGameDiscountRequest;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperGameResponse;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperPageResponse;
import com.arsio.developer.internal.infra.controller.dto.response.GetDiscountedGameResponse;
import com.arsio.developer.internal.infra.controller.mapper.DeveloperControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final ApplyGameDiscountUseCase applyGameDiscountUseCase;

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

        GetDiscountedGameResponse response = applyGameDiscountUseCase.execute(command);

        return ResponseEntity.ok(response);
    }
}
