package com.viniciusdev.controle_despesas.services;

import com.viniciusdev.controle_despesas.exceptions.BusinessException;
import com.viniciusdev.controle_despesas.exceptions.HandleEntityNotFoundException;
import com.viniciusdev.controle_despesas.model.Expense;
import com.viniciusdev.controle_despesas.model.FinancialReport;
import com.viniciusdev.controle_despesas.model.Specs.ExpenseSpecs;
import com.viniciusdev.controle_despesas.model.enumereds.ExpenseType;
import com.viniciusdev.controle_despesas.repositorys.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Slf4j
public class FinancialReportService {

    private final ExpenseRepository expenseRepository;

    public FinancialReport generateReport(UUID customerId, ExpenseType expenseType) {
        log.info("Generating financial report for customer: {}", customerId);
        try {

            Specification<Expense> spec = ExpenseSpecs.filter(customerId, expenseType);

            List<Expense> expenses = expenseRepository.findAll(spec);

            validateExpenses(expenses, customerId);


            BigDecimal totalAmount = calculateTotalAmount(expenses);

            FinancialReport financialReport = new FinancialReport();
            financialReport.setCreatedIn(LocalDateTime.now());
            financialReport.setExpenses(expenses);
            financialReport.setTotalAmount(totalAmount);

            log.debug("Financial report generated successfully for customer: {}", customerId);
            return financialReport;

        } catch (Exception e) {
            log.error("Error generating financial report for customer: {}", customerId);
            throw new BusinessException(e.getMessage(), e);
        }
    }


    private void validateExpenses(List<Expense> expenses, UUID customerId) {
        if (expenses.isEmpty()) {
            log.warn("No expenses found for customer: {}", customerId);
            throw new HandleEntityNotFoundException("No expenses found for customer: " + customerId);
        }
    }
    private BigDecimal calculateTotalAmount(List<Expense> expenses) {
        return expenses.stream()
                .map(Expense::getValue)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
