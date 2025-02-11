package com.viniciusdev.controle_despesas.services;

import com.viniciusdev.controle_despesas.exceptions.DuplicateRecordException;
import com.viniciusdev.controle_despesas.model.Customer;
import com.viniciusdev.controle_despesas.model.dtos.CustomerRequestDTO;
import com.viniciusdev.controle_despesas.model.dtos.mappers.CustomerMapper;
import com.viniciusdev.controle_despesas.repositorys.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository repository ;
    private final CustomerMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public ResponseEntity<Void> create( CustomerRequestDTO customerRequestDTO){
            validateRegistration(customerRequestDTO);
            Customer customer = mapper.toEntity(customerRequestDTO);
            customer.setPassword(bCryptPasswordEncoder.encode(customerRequestDTO.password()));
            repository.save(customer);
            log.info("Customer created successfully: " + customer.getId());
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private void validateRegistration(CustomerRequestDTO customerRequestDTO) {
        Optional<Customer> userNameIsPresent = repository.findByUsername(customerRequestDTO.username());
        if (userNameIsPresent.isPresent()){
            throw new DuplicateRecordException( "UserName" ,"UserName already in use");
        }
        log.info("Customer successfully validated");


    }

}
