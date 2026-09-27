package com.arsio.game.internal.infra.Controller.dto.request;

public record UpdateProcessorRequest(
        String model,
        String manufacturer
) {
}
