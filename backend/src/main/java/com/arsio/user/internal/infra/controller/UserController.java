package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.dto.ConfirmAvatarUploadCommand;
import com.arsio.user.internal.application.dto.UpdatePasswordCommand;
import com.arsio.user.internal.application.dto.UpdateUsernameCommand;
import com.arsio.user.internal.application.dto.UploadAvatarUrlCommand;
import com.arsio.user.internal.application.service.*;
import com.arsio.user.internal.infra.controller.dto.request.ConfirmAvatarUploadRequest;
import com.arsio.user.internal.infra.controller.dto.request.UploadAvatarUrlRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdatePasswordRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateUsernameRequest;
import com.arsio.user.internal.infra.controller.dto.response.*;
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
    private final FileUploadPreparationService fileUploadPreparationService;
    private final ConfirmAvatarUploadService confirmAvatarUploadService;

    @GetMapping("/me")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetUserResponse> getCurrentUser() {

        GetUserResponse response =
                getCurrentUserService.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetUserResponse> getById(@PathVariable("id") UUID id) {

        GetUserResponse response = getUserByIdService.execute(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me/avatar")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetAvatarResponse> getUserAvatar() {

        GetAvatarResponse response =
                getUserAvatarService.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me/profile")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetProfileResponse> getUserProfile() {

        GetProfileResponse response =
                getUserProfileService.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(response);
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

    @PostMapping("/me/avatar/upload-url")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<UploadAvatarUrlResponse> generateUploadUrl(@RequestBody @Valid UploadAvatarUrlRequest request) {

        UploadAvatarUrlCommand command = mapper.toUploadAvatarUrlCommand(request);

        UploadAvatarUrlResponse response =
                fileUploadPreparationService.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/me/avatar/confirm")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetAvatarResponse> confirmAvatarUpload(@RequestBody @Valid ConfirmAvatarUploadRequest request) {

        ConfirmAvatarUploadCommand command = mapper.toConfirmAvatarUploadCommand(request);

        GetAvatarResponse response =
                confirmAvatarUploadService.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }
}
