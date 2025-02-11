package com.viniciusdev.controle_despesas.repositorys;

import com.viniciusdev.controle_despesas.model.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository  extends JpaRepository<Customer, UUID> {



    Optional<Customer> findByUsername(String username);
}
