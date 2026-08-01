package com.arsio.shared.exception.model;

public record FieldValidationError(
        String field,
        String message
) {
}
