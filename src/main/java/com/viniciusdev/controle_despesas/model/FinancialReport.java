package com.viniciusdev.controle_despesas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Data
public class FinancialReport {

    private BigDecimal totalAmount;
    @NotNull
    @OneToMany
    private List<Expense> expenses;
    @NotNull
    private LocalDateTime createdIn;

}
