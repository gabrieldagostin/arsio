package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.service.GetCurrentUserService;
import com.arsio.user.internal.application.service.GetUserAvatarService;
import com.arsio.user.internal.application.service.GetUserByIdService;
import com.arsio.user.internal.infra.controller.dto.response.GetAvatarResponse;
import com.arsio.user.internal.infra.controller.dto.response.GetUserResponse;
import com.arsio.user.internal.infra.controller.mapper.UserControllerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserControllerMapper mapper;
    private final GetCurrentUserService getCurrentUserService;
    private final GetUserByIdService getUserByIdService;
    private final GetUserAvatarService getUserAvatarService;

    @GetMapping("/me")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetUserResponse> getCurrentUser() {

        GetUserResponse currentUser =
                getCurrentUserService.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/{id}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetUserResponse> getById(@PathVariable("id") UUID id) {

        GetUserResponse userResponse = getUserByIdService.execute(id);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/me/avatar")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetAvatarResponse> getUserAvatar() {

        GetAvatarResponse avatarResponse =
                getUserAvatarService.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(avatarResponse);
    }
}
