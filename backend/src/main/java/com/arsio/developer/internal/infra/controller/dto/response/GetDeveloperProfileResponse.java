package com.arsio.developer.internal.infra.controller.dto.response;

public record GetDeveloperProfileResponse(
        String displayName,
        String bio,
        String avatarUrl,
        String bannerUrl,
        String country
) {
}
