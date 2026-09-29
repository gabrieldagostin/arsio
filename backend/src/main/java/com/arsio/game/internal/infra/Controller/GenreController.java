package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.AssociateGenresCommand;
import com.arsio.game.internal.application.command.CreateGenreCommand;
import com.arsio.game.internal.application.command.DeleteGenreFromGameCommand;
import com.arsio.game.internal.application.command.UpdateGenreCommand;
import com.arsio.game.internal.application.usecase.*;
import com.arsio.game.internal.infra.Controller.dto.request.AssociateGenresRequest;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGenreRequest;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateGenreRequest;
import com.arsio.game.internal.infra.Controller.dto.response.GetGenreResponse;
import com.arsio.game.internal.infra.Controller.mapper.GenreControllerMapper;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-genres")
public class GenreController {

    private final GenreControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final ListGenresUseCase listGenresUseCase;
    private final AssociateGenresUseCase associateGenresUseCase;
    private final DeleteGenreFromGameUseCase deleteGenreFromGameUseCase;
    private final CreateGenreUseCase createGenreUseCase;
    private final UpdateGenreUseCase updateGenreUseCase;
    private final DeleteGenreUseCase deleteGenreUseCase;
    private final GetGameGenresUseCase getGameGenresUseCase;
    
    @GetMapping
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetGenreResponse>> findAll(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 20,
                    sort = "name",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetGenreResponse> response = listGenresUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{gameId}/genres")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Set<GetGenreResponse>> associateGenre(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid AssociateGenresRequest request) {

        AssociateGenresCommand command = mapper.toAssociateGenresCommand(request);

        Set<GetGenreResponse> responses = associateGenresUseCase.execute(gameId, command);

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{gameId}/genres/{genreId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteGenre(
            @PathVariable(name = "gameId") UUID gameId,
            @PathVariable("genreId") UUID genreId) {

        DeleteGenreFromGameCommand command = mapper.toDeleteGenreFromGameCommand(gameId, genreId);

        deleteGenreFromGameUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

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

    @GetMapping("/{gameId}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Set<GetGenreResponse>> getGenres(@PathVariable(name = "gameId") UUID gameId) {

        Set<GetGenreResponse> responses = getGameGenresUseCase.execute(gameId);

        return ResponseEntity.ok(responses);
    }
}
