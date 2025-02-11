package com.viniciusdev.controle_despesas.model.Specs;

import com.viniciusdev.controle_despesas.model.Expense;
import com.viniciusdev.controle_despesas.model.enumereds.ExpenseType;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ExpenseSpecs {

    public static Specification<Expense> hasCustomer(UUID customerId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("customer").get("id"), customerId);
    }
    public static Specification<Expense> hasExpenseType(ExpenseType expenseType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("expenseType"), expenseType);
    }


    public static Specification<Expense> filter(UUID customerId, ExpenseType expenseType) {
        Specification<Expense> spec = hasCustomer(customerId);
        if (expenseType != null) {
            spec = spec.and(hasExpenseType(expenseType));
        }
        return spec;
    }

}
