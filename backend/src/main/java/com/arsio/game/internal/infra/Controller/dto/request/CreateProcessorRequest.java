package com.arsio.game.internal.infra.Controller.dto.request;

public record CreateProcessorRequest(
        String model,
        String manufacturer
) {
}
