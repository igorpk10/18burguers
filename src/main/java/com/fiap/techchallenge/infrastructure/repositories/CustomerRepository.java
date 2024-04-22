package com.fiap.techchallenge.infrastructure.repositories;

import com.fiap.techchallenge.application.ports.infrastructure.ICustomerRepository;
import com.fiap.techchallenge.domain.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository implements ICustomerRepository {
    @Override
    public int create(Customer customer) {
        return 0;
    }

    @Override
    public Customer findByCpf(String cpf) {
        return null;
    }
}
