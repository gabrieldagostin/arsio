package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.service.GetCurrentUserService;
import com.arsio.user.internal.infra.controller.dto.response.CurrentUserResponse;
import com.arsio.user.internal.infra.controller.mapper.UserControllerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserControllerMapper mapper;
    private final GetCurrentUserService getCurrentUserService;

    @GetMapping("/me")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<CurrentUserResponse> getCurrentUser() {

        CurrentUserResponse currentUser =
                getCurrentUserService.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(currentUser);
    }
}
