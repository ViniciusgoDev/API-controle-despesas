package com.viniciusdev.controle_despesas.model.dtos;

import com.viniciusdev.controle_despesas.model.enumereds.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request object for creating a new customer")
public record CustomerRequestDTO(

        @Schema(description = "Username of the customer", example = "john_doe", required = true)
        @NotBlank(message = "Username cannot be null")
        @Size(max = 15, min = 5, message = "Username must be between 5 and 15 characters")
        String username,

        @Schema(description = "Password of the customer", example = "password123", required = true)
        @NotBlank(message = "Password cannot be null")
        @Size(max = 100, min = 8, message = "Password must be between 8 and 100 characters")
        String password,

        @Schema(description = "Role of the customer", example = "USER", required = true)
        @NotNull(message = "Role not defined")
        Role role
) {}