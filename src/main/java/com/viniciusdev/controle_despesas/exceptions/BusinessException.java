package com.viniciusdev.controle_despesas.exceptions;

import lombok.Getter;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(String additionalDescription, Throwable cause) {
        super(additionalDescription, cause);
    }
}
