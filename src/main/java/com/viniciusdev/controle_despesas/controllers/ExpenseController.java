package com.viniciusdev.controle_despesas.controllers;

import com.viniciusdev.controle_despesas.exceptions.HandleIllegalArgumentException;
import com.viniciusdev.controle_despesas.model.Expense;
import com.viniciusdev.controle_despesas.model.dtos.ExpenseRequestDTO;
import com.viniciusdev.controle_despesas.services.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Tag(name = "Expense Controller", description = "Endpoints for managing expenses")
@RequiredArgsConstructor
@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService service;

    @Operation(summary = "Create a new expense", description = "Endpoint to create a new expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expense created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ExpenseRequestDTO expenseRequestDTO) {
        service.create(expenseRequestDTO);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Delete an expense", description = "Endpoint to delete an expense by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Expense not found")
    })
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam @NotNull @Parameter(description = "Expense ID") UUID id) {
        return service.delete(id);
    }

    @Operation(summary = "List all expenses", description = "Endpoint to list all expenses for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of expenses returned successfully"),
            @ApiResponse(responseCode = "404", description = "No expenses found")
    })
    @GetMapping
    public List<Expense> searchAllExpense(@RequestParam @NotNull @Parameter(description = "User ID") UUID id) {
        return service.searchAllExpense(id);
    }

    @Operation(summary = "Update an expense", description = "Endpoint to update an existing expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Expense not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") @Parameter(description = "Expense ID") UUID id,
            @RequestBody @Valid ExpenseRequestDTO expenseRequestDTO) {
        return service.update(id, expenseRequestDTO);
    }
}