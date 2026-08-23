package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.dto.ConfirmFileUploadCommand;
import com.arsio.user.internal.application.dto.UploadFileUrlCommand;
import com.arsio.user.internal.application.service.*;
import com.arsio.user.internal.infra.controller.dto.request.ConfirmFileUploadRequest;
import com.arsio.user.internal.infra.controller.dto.request.UploadFileUrlRequest;
import com.arsio.user.internal.infra.controller.dto.response.GetAvatarResponse;
import com.arsio.user.internal.infra.controller.dto.response.GetBannerResponse;
import com.arsio.user.internal.infra.controller.dto.response.GetProfileResponse;
import com.arsio.user.internal.infra.controller.dto.response.UploadFileUrlResponse;
import com.arsio.user.internal.infra.controller.mapper.ProfileControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileControllerMapper mapper;
    private final GetUserAvatarService getUserAvatarService;
    private final GetUserProfileService getUserProfileService;
    private final FileUploadPreparationService fileUploadPreparationService;
    private final ConfirmAvatarUploadService confirmAvatarUploadService;
    private final ConfirmBannerUploadService confirmBannerUploadService;

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

    @PostMapping("/me/avatar/upload-url")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<UploadFileUrlResponse> generateAvatarUploadUrl(@RequestBody @Valid UploadFileUrlRequest request) {

        UploadFileUrlCommand command = mapper.toUploadFileUrlCommand(request);

        UploadFileUrlResponse response =
                fileUploadPreparationService.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/me/avatar/confirm")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetAvatarResponse> confirmAvatarUpload(@RequestBody @Valid ConfirmFileUploadRequest request) {

        ConfirmFileUploadCommand command = mapper.toConfirmFileUploadCommand(request);

        GetAvatarResponse response =
                confirmAvatarUploadService.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/me/banner/upload-url")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<UploadFileUrlResponse> generateBannerUploadUrl(@RequestBody @Valid UploadFileUrlRequest request) {

        UploadFileUrlCommand command = mapper.toUploadFileUrlCommand(request);

        UploadFileUrlResponse response =
                fileUploadPreparationService.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/me/banner/confirm")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetBannerResponse> confirmBannerUpload(@RequestBody @Valid ConfirmFileUploadRequest request) {

        ConfirmFileUploadCommand command = mapper.toConfirmFileUploadCommand(request);

        GetBannerResponse response =
                confirmBannerUploadService.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }
}
