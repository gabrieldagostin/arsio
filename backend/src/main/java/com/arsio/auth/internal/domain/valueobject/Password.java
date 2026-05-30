package com.arsio.auth.internal.domain.valueobject;

public record Password(String hashedValue) {

    public String getHashedValue() {
        return hashedValue;
    }
}
