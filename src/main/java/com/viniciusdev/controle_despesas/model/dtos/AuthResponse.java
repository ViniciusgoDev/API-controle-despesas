package com.viniciusdev.controle_despesas.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object containing the JWT token and its expiration time")
public record AuthResponse(
        @Schema(description = "Generated JWT token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,

        @Schema(description = "Expiration time of the token in seconds", example = "300")
        Long expiresIn
) {}