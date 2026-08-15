package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.dto.UpdatePasswordCommand;
import com.arsio.user.internal.application.dto.UpdateUsernameCommand;
import com.arsio.user.internal.application.service.*;
import com.arsio.user.internal.infra.controller.dto.request.UpdatePasswordRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateUsernameRequest;
import com.arsio.user.internal.infra.controller.dto.response.GetAvatarResponse;
import com.arsio.user.internal.infra.controller.dto.response.GetProfileResponse;
import com.arsio.user.internal.infra.controller.dto.response.GetUserResponse;
import com.arsio.user.internal.infra.controller.dto.response.UpdateUsernameResponse;
import com.arsio.user.internal.infra.controller.mapper.UserControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserControllerMapper mapper;
    private final GetCurrentUserService getCurrentUserService;
    private final GetUserByIdService getUserByIdService;
    private final GetUserAvatarService getUserAvatarService;
    private final GetUserProfileService getUserProfileService;
    private final UpdateUsernameService  updateUsernameService;
    private final UpdateUserPasswordHashService updatePasswordService;

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

    @GetMapping("/me/profile")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetProfileResponse> getUserProfile() {

        GetProfileResponse profileResponse =
                getUserProfileService.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(profileResponse);
    }

    @PatchMapping("/me/username")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<UpdateUsernameResponse> updateUsername(@RequestBody @Valid UpdateUsernameRequest request)  {

        UpdateUsernameCommand command = mapper.toUpdateUsernameCommand(request);

        UpdateUsernameResponse response =
                updateUsernameService.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me/password")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {

        UpdatePasswordCommand command = mapper.toUpdatePasswordCommand(request);

        updatePasswordService.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.noContent().build();
    }
}
