package com.arsio.developer.internal.infra.controller;

import com.arsio.developer.internal.application.usecase.GetDeveloperGamesUseCase;
import com.arsio.developer.internal.application.usecase.GetDeveloperPageUseCase;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperGamesResponse;
import com.arsio.developer.internal.infra.controller.dto.response.GetDeveloperPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final GetDeveloperPageUseCase getDeveloperPageUseCase;
    private final GetDeveloperGamesUseCase getDeveloperGamesUseCase;

    @GetMapping("/{developerId}")
    @PreAuthorize("HasRole('User')")
    public ResponseEntity<GetDeveloperPageResponse> getDeveloperPage(
            @PathVariable(name = "developerId") UUID developerId) {

        GetDeveloperPageResponse response = getDeveloperPageUseCase.execute(developerId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{developerId}/games")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<List<GetDeveloperGamesResponse>> getDeveloperGames(
            @PathVariable(name = "developerId") UUID developerId) {

        List<GetDeveloperGamesResponse> responses = getDeveloperGamesUseCase.execute(developerId);

        return ResponseEntity.ok(responses);
    }
}
