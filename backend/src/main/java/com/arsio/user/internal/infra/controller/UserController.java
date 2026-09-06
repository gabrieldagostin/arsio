package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.command.UpdatePasswordCommand;
import com.arsio.user.internal.application.command.UpdateUsernameCommand;
import com.arsio.user.internal.application.usecase.*;
import com.arsio.user.internal.domain.model.record.PageResult;
import com.arsio.user.internal.domain.model.record.Pagination;
import com.arsio.user.internal.infra.controller.dto.request.UpdatePasswordRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateUsernameRequest;
import com.arsio.user.internal.infra.controller.dto.response.*;
import com.arsio.user.internal.infra.controller.mapper.PaginationMapper;
import com.arsio.user.internal.infra.controller.mapper.UserControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserControllerMapper mapper;
    private final PaginationMapper paginationMapper;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final UpdateUsernameUseCase updateUsernameUseCase;
    private final UpdateUserPasswordHashUseCase updatePasswordService;
    private final DeactivateUserUseCase deactivateUserUseCase;

    @GetMapping("/me")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetUserResponse> getCurrentUser() {

        GetUserResponse response =
                getCurrentUserUseCase.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetUserResponse> getById(@PathVariable("id") UUID id) {

        GetUserResponse response = getUserByIdUseCase.execute(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResult<ListUserResponse>> findAll(
            @RequestParam(required = false) String search,
            @PageableDefault(
                    size = 20,
                    sort = "username",
                    direction = Sort.Direction.ASC
            ) Pageable pageable) {

        Pagination pagination = paginationMapper.toPagination(pageable);

        PageResult<ListUserResponse> response = listUsersUseCase.execute(search, pagination);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me/username")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<UpdateUsernameResponse> updateUsername(@RequestBody @Valid UpdateUsernameRequest request)  {

        UpdateUsernameCommand command = mapper.toUpdateUsernameCommand(request);

        UpdateUsernameResponse response =
                updateUsernameUseCase.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me/password")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {

        UpdatePasswordCommand command = mapper.toUpdatePasswordCommand(request);

        updatePasswordService.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/me/status")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> deactivateUser() {

        deactivateUserUseCase.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.noContent().build();
    }
}
