package com.viniciusdev.controle_despesas.model.dtos;

import com.viniciusdev.controle_despesas.model.enumereds.ExpenseType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Request object for creating or updating an expense")
public record ExpenseRequestDTO(

        @Schema(description = "Description of the expense", example = "Monthly grocery shopping", required = true)
        @NotBlank(message = "Description cannot be null or empty")
        @Size(min = 5, message = "Description must have at least 5 characters")
        String description,

        @Schema(description = "Value of the expense", example = "150.75", required = true)
        @NotNull(message = "Value cannot be null")
        @Positive(message = "Value must be positive and greater than 0")
        BigDecimal value,

        @Schema(description = "ID of the customer associated with the expense", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
        @NotNull(message = "Customer ID cannot be null")
        UUID customerId,

        @Schema(description = "Type of the expense", example = "FOOD", required = true)
        @NotNull(message = "Expense type cannot be null")
        ExpenseType expenseType
) {}