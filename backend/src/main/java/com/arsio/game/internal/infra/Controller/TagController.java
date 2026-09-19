package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.usecase.ListTagsUseCase;
import com.arsio.game.internal.infra.Controller.dto.response.TagResponse;
import com.arsio.game.internal.infra.Controller.mapper.TagControllerMapper;
import com.arsio.shared.pagination.PageResult;
import com.arsio.shared.pagination.Pagination;
import com.arsio.shared.pagination.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-tags")
public class TagController {

    private final TagControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final ListTagsUseCase listTagsUseCase;

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
}
