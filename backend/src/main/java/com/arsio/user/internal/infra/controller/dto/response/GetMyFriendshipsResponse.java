package com.arsio.user.internal.infra.controller.dto.response;

import java.util.UUID;

public record GetMyFriendshipsResponse(
        UUID friendshipId,
        FriendResponse friendResponse
) {
}
