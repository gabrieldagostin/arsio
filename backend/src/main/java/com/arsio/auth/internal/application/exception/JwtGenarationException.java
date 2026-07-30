package com.arsio.auth.internal.application.exception;

public class JwtGenarationException extends RuntimeException {
    public JwtGenarationException(String message) {
        super(message);
    }
}
