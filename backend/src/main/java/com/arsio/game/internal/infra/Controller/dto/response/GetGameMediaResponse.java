package com.arsio.game.internal.infra.Controller.dto.response;

import java.util.Set;

public record GetGameMediaResponse(
        String thumbnailUrl,
        String bannerUrl,
        Set<String> screenshotUrl,
        String trailerUrl
) {
}
