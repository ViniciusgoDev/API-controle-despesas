package com.viniciusdev.controle_despesas.model.dtos.mappers;

import com.viniciusdev.controle_despesas.model.Expense;

import com.viniciusdev.controle_despesas.model.dtos.ExpenseRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExpenseMapper {

    public Expense toEntity (ExpenseRequestDTO requestDTO){
        Expense expense = new Expense();
        expense.setValue(requestDTO.value());
        expense.setExpenseType(requestDTO.expenseType());
        expense.setDescription(requestDTO.description());
        expense.setCreatedIn(LocalDateTime.now());

        return expense;
    }


}
