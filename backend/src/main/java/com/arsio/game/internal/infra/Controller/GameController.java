package com.arsio.game.internal.infra.Controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.game.internal.application.command.ChangeGamePriceCommand;
import com.arsio.game.internal.application.command.CreateGameCommand;
import com.arsio.game.internal.application.command.UpdateGameCommand;
import com.arsio.game.internal.application.usecase.*;
import com.arsio.game.internal.infra.Controller.dto.request.ChangeGamePriceRequest;
import com.arsio.game.internal.infra.Controller.dto.request.CreateGameRequest;
import com.arsio.game.internal.infra.Controller.dto.response.*;
import com.arsio.game.internal.infra.Controller.dto.request.UpdateGameRequest;
import com.arsio.game.internal.infra.Controller.mapper.GameControllerMapper;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import com.arsio.shared.storage.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/games")
public class GameController {

    private final GameControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final CreateGameUseCase registerGameUseCase;
    private final GetGameUseCase getGameUseCase;
    private final ListGamesUseCase listGamesUseCase;
    private final UpdateGameUseCase updateGameUseCase;
    private final SearchGamesUseCase searchGamesUseCase;
    private final ChangeGamePriceUseCase changeGamePriceUseCase;
    private final GetFeaturedGamesUseCase getFeaturedGamesUseCase;
    private final GetNewReleasedGamesUseCase getNewReleasedGamesUseCase;
    private final GetDiscountedGamesUseCase getDiscountedGamesUseCase;
    private final GetBestSellingGamesUseCase getBestSellingGamesUseCase;
    private final GetTopRatedGamesUseCase getTopRatedGamesUseCase;
    private final GameBuildUploadPreparationUseCase gameBuildUploadPreparationUseCase;
    private final ConfirmGameBuildUploadUseCase confirmGameBuildUploadUseCase;
    private final PublishGameUseCase publishGameUseCase;

    @PostMapping
    @PreAuthorize("hasRole('DEV')")
    public ResponseEntity<CreateGameResponse> create(@RequestBody @Valid CreateGameRequest request, UriComponentsBuilder builder) {

        CreateGameCommand command = mapper.toCreateGameCommand(request);

        CreateGameResponse response =
                registerGameUseCase.execute(SecurityUtils.getCurrentUserId(), command);

        URI uri = builder.path("/games/{id}")
                .buildAndExpand(response).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{gameId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GetGameResponse> get(@PathVariable(name = "gameId") UUID gameId) {

        GetGameResponse response = getGameUseCase.execute(gameId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<PageResult<ListGamesResponse>> findAll(
            @PageableDefault(size = 20)Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<ListGamesResponse> responses = listGamesUseCase.execute(pagination);

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{gameId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UpdateGameResponse> update(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid UpdateGameRequest request) {

        UpdateGameCommand command = mapper.toUpdateGameCommand(gameId, request);

        UpdateGameResponse response = updateGameUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<PageResult<ListGamesResponse>> search(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 5,
                    sort = "title",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<ListGamesResponse> responses = searchGamesUseCase.execute(search, pagination);

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{gameId}/price")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<ChangeGamePriceResponse> changePrice(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid ChangeGamePriceRequest request) {

        ChangeGamePriceCommand command = mapper.toChangeGamePriceCommand(gameId, request);

        ChangeGamePriceResponse response = changeGamePriceUseCase.execute(command);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/featured")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<PageResult<ListGamesResponse>> featured(
            @PageableDefault(
                    size = 20,
                    sort = "title",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<ListGamesResponse> responses = getFeaturedGamesUseCase.execute(pagination);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/new-releases")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<PageResult<ListGamesResponse>> newReleases(
            @PageableDefault(
                    size = 20,
                    sort = "title",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<ListGamesResponse> responses = getNewReleasedGamesUseCase.execute(pagination);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/discounts")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<PageResult<ListGamesResponse>> discounts(
            @PageableDefault(
                    size = 20,
                    sort = "title",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<ListGamesResponse> responses = getDiscountedGamesUseCase.execute(pagination);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/best-sellers")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<List<ListGamesResponse>> bestSellers(
            @PageableDefault(size = 20) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        List<ListGamesResponse> responses = getBestSellingGamesUseCase.execute(pagination);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("top-rated")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<List<ListGamesResponse>> topRated(
            @PageableDefault(size = 20) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        List<ListGamesResponse> responses = getTopRatedGamesUseCase.execute(pagination);

        return ResponseEntity.ok(responses);
    }

    @PostMapping("{gameId}/builds/upload-url")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<UploadFileUrlResponse> generateBuildUploadUrl(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid UploadFileUrlRequest request) {

        UploadFileUrlCommand command = mapper.toUploadFileUrlCommand(request);

        UploadFileUrlResponse response = gameBuildUploadPreparationUseCase.execute(gameId, command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}/builds/confirm")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<GameBuildUploadCompletionResponse> confirmGameBuildUpload(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid ConfirmFileUploadRequest request) {

        ConfirmFileUploadCommand command = mapper.toConfirmFileUploadCommand(request);

        GameBuildUploadCompletionResponse response =
                confirmGameBuildUploadUseCase.execute(gameId, command);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{gameId}/publish")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> publishGameBuild(@PathVariable(name = "gameId") UUID gameId) {

        publishGameUseCase.execute(gameId);

        return ResponseEntity.ok().build();
    }
}
