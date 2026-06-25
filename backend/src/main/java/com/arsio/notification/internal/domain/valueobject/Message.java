package com.arsio.notification.internal.domain.valueobject;

import com.arsio.notification.internal.domain.exception.InvalidMessageException;

public record Message(String value) {

    public Message {
        validate(value);
    }

    private String validate(String value) {
        if (value.isBlank() || value == null) throw new InvalidMessageException("Mensagem da Notificação Inválida");

        return value;
    }
}
