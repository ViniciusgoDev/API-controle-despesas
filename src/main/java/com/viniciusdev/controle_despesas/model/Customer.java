package com.viniciusdev.controle_despesas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viniciusdev.controle_despesas.model.dtos.AuthRequest;
import com.viniciusdev.controle_despesas.model.enumereds.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "userName", nullable = false, unique = true)
    private String username;


    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Expense> expensesList;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreatedDate
    @PastOrPresent
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_in", nullable = false, updatable = false)
    private LocalDateTime createdIn;


    public boolean isLoginCorrect(AuthRequest authRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(authRequest.password(), this.password);
    }

}
