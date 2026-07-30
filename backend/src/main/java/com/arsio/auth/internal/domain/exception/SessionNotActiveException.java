package com.arsio.auth.internal.domain.exception;

public class SessionNotActiveException extends RuntimeException {
    public SessionNotActiveException(String message) {
        super(message);
    }
}
