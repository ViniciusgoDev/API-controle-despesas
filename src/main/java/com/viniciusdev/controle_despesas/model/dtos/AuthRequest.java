package com.viniciusdev.controle_despesas.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @Schema(description = "Username of the user", example = "john_doe", required = true)
        @NotBlank(message = "Username cannot be null or empty")
        String username,

        @Schema(description = "Password of the user", example = "password123", required = true)
        @NotBlank(message = "Password cannot be null or empty")
        String password
) {}