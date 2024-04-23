package com.fiap.techchallenge.application.ports.infrastructure;

import com.fiap.techchallenge.domain.entities.Customer;

import java.util.Optional;

public interface ICustomerRepository {
    int create(Customer customer);

    Optional<Customer> findByCpf(String cpf);
}
