package com.arsio.shared.exception;

public class InvalidUserRoleException extends RuntimeException {

    public InvalidUserRoleException() {
        super("Usuário com papel inválido ou não definido!");
    }

    public InvalidUserRoleException(String message) {
        super(message);
    }
}
