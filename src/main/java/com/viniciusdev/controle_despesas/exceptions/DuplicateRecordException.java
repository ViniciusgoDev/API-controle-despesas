package com.viniciusdev.controle_despesas.exceptions;

import lombok.Getter;

public class DuplicateRecordException extends RuntimeException {

    @Getter
    private String field;

    public DuplicateRecordException(String field , String message) {
        super(message);
        this.field =field;
    }
}
