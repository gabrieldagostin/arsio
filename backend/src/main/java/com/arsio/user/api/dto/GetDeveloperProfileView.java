package com.arsio.user.api.dto;

public record GetDeveloperProfileView(
        String displayName,
        String bio,
        String avatarUrl,
        String bannerUrl,
        String country
) {
}
