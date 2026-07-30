package com.arsio.auth.internal.application.exception;

public class JwtParsingException extends RuntimeException {
    public JwtParsingException(String message) {
        super(message);
    }
}
