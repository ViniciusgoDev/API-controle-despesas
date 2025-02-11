package com.viniciusdev.controle_despesas.controllers;

import com.viniciusdev.controle_despesas.model.dtos.CustomerRequestDTO;
import com.viniciusdev.controle_despesas.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer", description = "Endpoints for managing customers")
@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    @Operation(summary = "Create a new customer", description = "Endpoint to create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        return service.create(customerRequestDTO);
    }
}