package com.viniciusdev.controle_despesas.model.dtos;

import com.viniciusdev.controle_despesas.model.enumereds.ExpenseType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Response object representing an expense")
public record ExpenseResponseDTO(
        @Schema(description = "Description of the expense", example = "Monthly grocery shopping")
        String description,

        @Schema(description = "Value of the expense", example = "150.75")
        BigDecimal value,

        @Schema(description = "Type of the expense", example = "FOOD")
        ExpenseType expenseType,

        @Schema(description = "Unique identifier of the expense", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "Timestamp when the expense was created", example = "2023-10-01T12:34:56")
        LocalDateTime createdIn
) {}