package com.viniciusdev.controle_despesas.exceptions;

import lombok.Getter;

public class HandleIllegalArgumentException extends RuntimeException {

    @Getter
    private final String field;

    public HandleIllegalArgumentException(String field, String message) {
        super(message);
        this.field = field;

    }
}
