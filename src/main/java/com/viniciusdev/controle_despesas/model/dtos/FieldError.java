package com.viniciusdev.controle_despesas.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a field error during validation.")
public record FieldError(

        @Schema(description = "Name of the field that caused the error", example = "email")
        String field,

        @Schema(description = "Error message associated with the field", example = "Email is required")
        String error) {
}
