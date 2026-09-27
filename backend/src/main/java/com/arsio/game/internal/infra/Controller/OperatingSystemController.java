package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.usecase.ListOperatingSystemsUseCase;
import com.arsio.game.internal.infra.Controller.dto.response.GetOperatingSystemResponse;
import com.arsio.game.internal.infra.Controller.mapper.OperatingSystemControllerMapper;
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
@RequestMapping("/game-requirements/operating-systems")
public class OperatingSystemController {

    private final OperatingSystemControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final ListOperatingSystemsUseCase listOperatingSystemsUseCase;

    @GetMapping
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetOperatingSystemResponse>> getAll(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 10,
                    sort = "name",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetOperatingSystemResponse> response =
                listOperatingSystemsUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }
}
