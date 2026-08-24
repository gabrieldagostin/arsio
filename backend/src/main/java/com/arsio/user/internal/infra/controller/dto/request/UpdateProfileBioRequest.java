package com.arsio.user.internal.infra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProfileBioRequest(
        @NotBlank(message = "${profileBio.required}")
        @Size(min = 1, max = 300, message = "${profileBio.size}")
        String newBio
) {
}
