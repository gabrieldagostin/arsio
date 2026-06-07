package com.arsio.user.internal.domain.exception;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super("Email inválido");
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
