package com.arsio.user.internal.infra.controller.dto.response;

public record GetProfileResponse(
        String displayName,
        String bio,
        String profileImageKey,
        String profileBannerKey,
        String country
) {
}
