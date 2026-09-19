package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.AssociateGenresCommand;
import com.arsio.game.internal.application.command.DeleteGenreFromGameCommand;
import com.arsio.game.internal.application.usecase.AssociateGenresUseCase;
import com.arsio.game.internal.application.usecase.DeleteGenreFromGameUseCase;
import com.arsio.game.internal.application.usecase.ListGenresUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.AssociateGenresRequest;
import com.arsio.game.internal.infra.Controller.dto.response.GenreResponse;
import com.arsio.game.internal.infra.Controller.mapper.GenreControllerMapper;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    
    @GetMapping
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GenreResponse>> findAll(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 20,
                    sort = "name",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GenreResponse> response = listGenresUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{gameId}/genres")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Set<GenreResponse>> associateGenre(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid AssociateGenresRequest request
    ) {

        AssociateGenresCommand command = mapper.toAssociateGenresCommand(request);

        Set<GenreResponse> response = associateGenresUseCase.execute(gameId, command);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{gameId}/genres/{genreId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteGenre(
            @PathVariable(name = "gameId") UUID gameId,
            @PathVariable("genreId") UUID genreId
    ) {

        DeleteGenreFromGameCommand command = mapper.toDeleteGenreFromGameCommand(gameId, genreId);

        deleteGenreFromGameUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }
}
