package com.arsio.user.internal.domain.exception;

public class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException() {
        super("Nome de usuário inválido");
    }
    public InvalidUsernameException(String message) {
        super(message);
    }
}
