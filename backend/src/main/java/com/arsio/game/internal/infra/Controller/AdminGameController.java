package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.usecase.ArchiveGameUseCase;
import com.arsio.game.internal.application.usecase.FeatureGameUseCase;
import com.arsio.game.internal.application.usecase.UnFeatureGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/games")
public class AdminGameController {

    private final ArchiveGameUseCase archiveGameUseCase;
    private final FeatureGameUseCase featureGameUseCase;
    private final UnFeatureGameUseCase unFeatureGameUseCase;

    @PatchMapping("/{gameId}/archive")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Void> archive(@PathVariable(name = "gameId") UUID gameId) {

        archiveGameUseCase.execute(gameId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gameId}/feature")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Void> feature(@PathVariable(name = "gameId") UUID gameId) {

        featureGameUseCase.execute(gameId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{gameId}/unfeature")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Void> unFeature(@PathVariable(name = "gameId") UUID gameId) {

        unFeatureGameUseCase.execute(gameId);

        return ResponseEntity.noContent().build();
    }
}
