package com.arsio.game.internal.infra.Controller;

import com.arsio.game.internal.application.usecase.ListProcessorsUseCase;
import com.arsio.game.internal.infra.Controller.dto.response.GetProcessorResponse;
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
@RequestMapping("/game-requirements/processors")
public class ProcessorController {

    private final PaginationMapper paginationMapper;
    private final ListProcessorsUseCase listProcessorsUseCase;

    @GetMapping
    @PreAuthorize("HasRole('DEV')")
    public ResponseEntity<PageResult<GetProcessorResponse>> findAll(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 10,
                    sort = "model",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<GetProcessorResponse> response =
                listProcessorsUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }
}
