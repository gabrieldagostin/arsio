package com.arsio.notification.internal.domain.valueobject;

import com.arsio.notification.internal.domain.exception.InvalidTitleException;

public record Title(String value) {

    public Title {
        validate(value);
    }

    private String validate(String value) {
        if (value.isBlank() || value == null) throw new InvalidTitleException("Titulo de Notificação Inválido");

        return value;
    }
}
