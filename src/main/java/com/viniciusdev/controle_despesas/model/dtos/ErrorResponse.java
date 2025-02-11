package com.viniciusdev.controle_despesas.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Represents an error response returned by the API.")
public record ErrorResponse(

        @Schema(description = "HTTP status code representing the error", example = "400")
        int status,

        @Schema(description = "Message providing additional details about the error", example = "Invalid request")
        String message,

        @Schema(description = "List of field errors associated with the validation failures", implementation = FieldError.class)
        List<FieldError> errors) {
}
