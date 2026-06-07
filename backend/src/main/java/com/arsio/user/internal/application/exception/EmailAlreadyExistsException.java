package com.arsio.user.internal.application.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email já cadastrado!");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
