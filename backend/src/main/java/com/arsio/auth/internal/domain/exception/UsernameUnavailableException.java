package com.arsio.auth.internal.domain.exception;

public class UsernameUnavailableException extends RuntimeException {

    public UsernameUnavailableException() {
        super("Nome de usuário indisponível");
    }

    public UsernameUnavailableException(String message) {
        super(message);
    }
}
