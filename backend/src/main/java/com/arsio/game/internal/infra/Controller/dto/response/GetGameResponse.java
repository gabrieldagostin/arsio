package com.arsio.game.internal.infra.Controller.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record GetGameResponse(
        UUID gameId,
        UUID developerId,
        String title,
        String description,
        BigDecimal price,
        String status,
        LocalDate releaseDate,
        GetGameMediaResponse gameMedia,
        Set<GetGenreResponse> gameGenres,
        Set<GetTagResponse> gameTags,
        GetGameRequirementsResponse gameRequirements
) {
}
