package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.usecase.ArchiveGameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/games")
public class AdminGameController {

    private final ArchiveGameUseCase archiveGameUseCase;

    @PatchMapping("/{gameId}/archive")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Void> archive(@PathVariable(name = "gameId") UUID gameId) {

        archiveGameUseCase.execute(gameId);

        return ResponseEntity.noContent().build();
    }
}
