package com.arsio.user.internal.infra.controller;

import com.arsio.config.security.SecurityUtils;
import com.arsio.user.internal.application.command.ConfirmFileUploadCommand;
import com.arsio.user.internal.application.command.UpdateProfileBioCommand;
import com.arsio.user.internal.application.command.UpdateProfileCountryCommand;
import com.arsio.user.internal.application.command.UploadFileUrlCommand;
import com.arsio.user.internal.application.service.FileUploadPreparationService;
import com.arsio.user.internal.application.usecase.*;
import com.arsio.user.internal.infra.controller.dto.request.ConfirmFileUploadRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateProfileBioRequest;
import com.arsio.user.internal.infra.controller.dto.request.UpdateProfileCountryRequest;
import com.arsio.user.internal.infra.controller.dto.request.UploadFileUrlRequest;
import com.arsio.user.internal.infra.controller.dto.response.*;
import com.arsio.user.internal.infra.controller.mapper.ProfileControllerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileControllerMapper mapper;
    private final GetUserAvatarUseCase getUserAvatarUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final UpdateProfileBioUseCase updateProfileBioUseCase;
    private final FileUploadPreparationService fileUploadPreparationService;
    private final ConfirmAvatarUploadUseCase confirmAvatarUploadUseCase;
    private final ConfirmBannerUploadUseCase confirmBannerUploadUseCase;
    private final UpdateProfileCountryUseCase updateProfileCountryUseCase;

    @GetMapping("/me/avatar")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetAvatarResponse> getUserAvatar() {

        GetAvatarResponse response =
                getUserAvatarUseCase.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me/profile")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<GetProfileResponse> getUserProfile() {

        GetProfileResponse response =
                getUserProfileUseCase.execute(SecurityUtils.getCurrentUserId());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me/bio")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<UpdateProfileBioResponse> updateProfileBio(@RequestBody @Valid UpdateProfileBioRequest request) {

        UpdateProfileBioCommand command = mapper.toUpdateProfileBioCommand(request);

        UpdateProfileBioResponse response =
                updateProfileBioUseCase.execute(SecurityUtils.getCurrentUserId(), command);

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
                confirmAvatarUploadUseCase.execute(SecurityUtils.getCurrentUserId(), command);

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
                confirmBannerUploadUseCase.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me/country")
    @PreAuthorize("HasRole('USER')")
    public ResponseEntity<UpdateProfileCountryResponse> updateProfileCountry(@RequestBody @Valid UpdateProfileCountryRequest request) {

        UpdateProfileCountryCommand command = mapper.toUpdateProfileCountryCommand(request);

        UpdateProfileCountryResponse response =
                updateProfileCountryUseCase.execute(SecurityUtils.getCurrentUserId(), command);

        return ResponseEntity.ok(response);
    }
}
