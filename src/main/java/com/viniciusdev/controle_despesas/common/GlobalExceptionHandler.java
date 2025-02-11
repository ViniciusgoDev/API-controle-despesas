package com.viniciusdev.controle_despesas.common;

import com.viniciusdev.controle_despesas.exceptions.*;
import com.viniciusdev.controle_despesas.model.dtos.ErrorResponse;
import com.viniciusdev.controle_despesas.model.dtos.FieldError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new FieldError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "\n" +
                        "Validation Error",
                fieldErrors
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(HandleEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(HandleEntityNotFoundException e) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Entity not found",
                List.of(new FieldError("", e.getMessage()))
        );
    }


    @ExceptionHandler(HandleIllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(HandleIllegalArgumentException e) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid argument error",
                List.of(new FieldError(e.getField(), e.getMessage()))
        );
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse BusinessException(BusinessException e) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                        "Error generating report",
                List.of(new FieldError( "", e.getMessage()))
        );
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid UUID format",
               List.of(new FieldError("", e.getMessage()  ))
        );
    }
    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateRecordException(DuplicateRecordException e){
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Duplicate Record",
                List.of(new FieldError(e.getField() , e.getMessage()
                ))
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDefaultHandlerExceptionResolver(HttpMessageNotReadableException e){
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid field",
                List.of(new FieldError(
                        "", e.getMessage()
                ))
        );
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidCredentialsException(InvalidCredentialsException e){
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid credentials",
                List.of(new FieldError(
                        "", e.getMessage()
                ))
        );
    }

}




