package com.viniciusdev.controle_despesas.repositorys;

import com.viniciusdev.controle_despesas.model.Expense;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID>, JpaSpecificationExecutor<Expense> {

    Optional<Expense> findById(UUID id);

    List<Expense> findByCustomerId(UUID customer);

    List<Expense> findAll(Specification<Expense> spec);

    List<Expense> findAllById(UUID id);



}
