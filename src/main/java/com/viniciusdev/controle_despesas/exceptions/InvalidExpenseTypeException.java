package com.viniciusdev.controle_despesas.exceptions;

import lombok.Getter;

public class InvalidExpenseTypeException extends RuntimeException {

    @Getter
    private String field;

    public InvalidExpenseTypeException( String field,String message) {
        super(message);
        this.field = field;
    }
}
