package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.CreateGenreCommand;
import com.arsio.game.internal.application.command.UpdateGenreCommand;
import com.arsio.game.internal.application.usecase.CreateGenreUseCase;
import com.arsio.game.internal.application.usecase.DeleteGenreUseCase;
import com.arsio.game.internal.application.usecase.UpdateGenreUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGenreRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateGenreRequest;
import com.arsio.game.internal.infra.Controller.dto.response.GetGenreResponse;
import com.arsio.game.internal.infra.Controller.mapper.GenreControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/game-genres")
public class AdminGenreController {

    private final GenreControllerMapper mapper;
    private final CreateGenreUseCase createGenreUseCase;
    private final UpdateGenreUseCase updateGenreUseCase;
    private final DeleteGenreUseCase deleteGenreUseCase;

    @PostMapping
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetGenreResponse> create(@RequestBody @Valid CreateGenreRequest request) {

        CreateGenreCommand command = mapper.toCreateGenreCommand(request);

        GetGenreResponse response = createGenreUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{genreId}")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<GetGenreResponse> update(
            @PathVariable(name = "genreId") UUID genreId,
            @RequestBody @Valid UpdateGenreRequest request) {

        UpdateGenreCommand command = mapper.toUpdateGenreCommand(genreId, request);

        GetGenreResponse response = updateGenreUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{genreId}")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable(name = "genreId") UUID genreId) {

        deleteGenreUseCase.execute(genreId);

        return ResponseEntity.noContent().build();
    }
}
