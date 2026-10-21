package com.arsio.review.internal.domain.valueobject;

public record Comment(String value) {

    public Comment {
        value = value == null ? null : value.trim();
    }
}
