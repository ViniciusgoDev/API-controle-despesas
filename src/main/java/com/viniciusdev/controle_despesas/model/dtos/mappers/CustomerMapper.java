package com.viniciusdev.controle_despesas.model.dtos.mappers;


import com.viniciusdev.controle_despesas.model.Customer;
import com.viniciusdev.controle_despesas.model.dtos.CustomerRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {


    public Customer toEntity(CustomerRequestDTO customerRequestDTO) {

        Customer customer = new Customer();
        customer.setUsername(customerRequestDTO.username());
        customer.setPassword(customerRequestDTO.password());
        customer.setRole(customerRequestDTO.role());
        return customer;
    }
}
