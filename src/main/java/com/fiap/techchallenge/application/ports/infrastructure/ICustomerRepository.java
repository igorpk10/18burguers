package com.fiap.techchallenge.application.ports.infrastructure;

import com.fiap.techchallenge.domain.entities.Customer;

public interface ICustomerRepository {
    int create(Customer customer);

    Customer findByCpf(String cpf);
}
