package com.viniciusdev.controle_despesas.services;

import com.viniciusdev.controle_despesas.exceptions.HandleEntityNotFoundException;
import com.viniciusdev.controle_despesas.exceptions.HandleIllegalArgumentException;
import com.viniciusdev.controle_despesas.model.Customer;
import com.viniciusdev.controle_despesas.model.Expense;
import com.viniciusdev.controle_despesas.model.dtos.ExpenseRequestDTO;
import com.viniciusdev.controle_despesas.model.dtos.mappers.ExpenseMapper;
import com.viniciusdev.controle_despesas.repositorys.CustomerRepository;
import com.viniciusdev.controle_despesas.repositorys.ExpenseRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ExpenseService {

    private final ExpenseRepository repository;
    private final ExpenseMapper mapper;
    private final CustomerRepository customerRepository;


    public void create(ExpenseRequestDTO expenseRequestDTO) {
        log.info("Starting expense creation !");
        Customer customer = customerRepository.findById(expenseRequestDTO.customerId())
                .orElseThrow(() -> new HandleEntityNotFoundException("Customer not found"));
        if (expenseRequestDTO.expenseType() == null || expenseRequestDTO.expenseType().toString().trim().isEmpty()) {
            throw new HandleIllegalArgumentException(" Expense Type", "Expense type invalid");
        }
        Expense expense = mapper.toEntity(expenseRequestDTO);
        expense.setCustomer(customer);
        log.info("Expense created successfully: " + expense.getId() );
        repository.save(expense);
    }
    @Transactional
    public ResponseEntity<Void> delete(UUID id) {
        log.info("Starting expense deletion" + id );
        Expense expense = repository.findById(id)
                .orElseThrow(() -> new HandleEntityNotFoundException("Expense not found with id: " + id));
        repository.deleteById(id);
        log.info("Expense deleted successfully" + id );
        return ResponseEntity.ok().build();
    }

    public List<Expense> searchAllExpense(UUID id) {
        log.info("Searching for customer expenses" + id );
      return repository.findAllById(id);
    }

    @Transactional
    public ResponseEntity<?> update(UUID id, ExpenseRequestDTO expenseRequestDTO) {
        Expense expense = repository.findById(id)
                .orElseThrow(() -> new HandleEntityNotFoundException("Expense not found with id: " + id));
        expense.setDescription(expenseRequestDTO.description());
        expense.setValue(expenseRequestDTO.value());
        log.info("customer data updated successfully");
        return ResponseEntity.ok().build();
    }

}
