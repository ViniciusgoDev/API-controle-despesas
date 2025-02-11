package com.viniciusdev.controle_despesas.controllers;

import com.viniciusdev.controle_despesas.exceptions.InvalidCredentialsException;
import com.viniciusdev.controle_despesas.model.dtos.AuthRequest;
import com.viniciusdev.controle_despesas.model.dtos.AuthResponse;
import com.viniciusdev.controle_despesas.model.enumereds.Role;
import com.viniciusdev.controle_despesas.repositorys.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Tag(name = "Authentication", description = "Endpoints for user authentication and token generation")
@RequiredArgsConstructor
@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Operation(summary = "Authenticate user", description = "Authenticate a user and generate a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        var customerOptional = customerRepository.findByUsername(authRequest.username())
                .orElseThrow(() -> new InvalidCredentialsException("User or password is invalid!"));

        if (!customerOptional.isLoginCorrect(authRequest, bCryptPasswordEncoder)) {
            throw new InvalidCredentialsException("User or password is invalid!");
        }

        Role role = customerOptional.getRole();

        var expiresIn = 300L;
        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .issuer("api-controle-despensas")
                .subject(customerOptional.getId().toString())
                .claim("role", role)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new AuthResponse(jwtValue, expiresIn));
    }
}