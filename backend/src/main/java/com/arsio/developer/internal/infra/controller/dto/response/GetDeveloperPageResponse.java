package com.arsio.developer.internal.infra.controller.dto.response;

import java.util.List;

public record GetDeveloperPageResponse(
        GetDeveloperProfileResponse developerProfile,
        List<GetDeveloperGameResponse> games
) {
}
