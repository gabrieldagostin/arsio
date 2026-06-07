package com.arsio.user.internal.domain.exception;

public class InvalidUsernameLengthException extends RuntimeException {

    public InvalidUsernameLengthException() {
        super("Nome de usuário deve ter entre 6 e 50 caracteres!");
    }

    public InvalidUsernameLengthException(String message) {
        super(message);
    }
}
