package com.arsio.user.internal.domain.valueobject;

public record ObjectKey(String value) {

    public ObjectKey {
        value = value == null ? "" : value.trim();
        if (value.isBlank()) {
            throw new IllegalArgumentException("Object key cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "ProfileObjectKey[PROTECTED]";
    }
}
