package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.command.AssociateTagsCommand;
import com.arsio.game.internal.application.command.DeleteTagFromGameCommand;
import com.arsio.game.internal.application.usecase.AssociateTagsUseCase;
import com.arsio.game.internal.application.usecase.DeleteTagFromGameUseCase;
import com.arsio.game.internal.application.usecase.ListTagsUseCase;
import com.arsio.game.internal.infra.Controller.dto.request.AssociateTagsRequest;
import com.arsio.game.internal.infra.Controller.dto.response.TagResponse;
import com.arsio.game.internal.infra.Controller.mapper.TagControllerMapper;
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
@RequestMapping("/game-tags")
public class TagController {

    private final TagControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final ListTagsUseCase listTagsUseCase;
    private final AssociateTagsUseCase associateTagsUseCase;
    private final DeleteTagFromGameUseCase deleteTagFromGameUseCase;

    @GetMapping
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<TagResponse>> findAll(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 20,
                    sort = "name",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<TagResponse> response = listTagsUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gameId}/tags")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Set<TagResponse>> associateTag(
            @PathVariable(name = "gameId") UUID gameId,
            @RequestBody @Valid AssociateTagsRequest request
    ) {

        AssociateTagsCommand command = mapper.toAssociateTagsCommand(request);

        Set<TagResponse> responses = associateTagsUseCase.execute(gameId, command);

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{gameId}/tags/{tagId}")
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<Void> deleteTag(
            @PathVariable(name = "gameId") UUID gameId,
            @PathVariable(name = "tagId") UUID tagId
    ) {

        DeleteTagFromGameCommand command = mapper.toDeleteTagFromGameCommand(gameId, tagId);

        deleteTagFromGameUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }
}
