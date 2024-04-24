package com.fiap.techchallenge.infrastructure.repositories;

import com.fiap.techchallenge.application.ports.infrastructure.ICustomerRepository;
import com.fiap.techchallenge.domain.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostgresCustomerRepository implements ICustomerRepository {

    @Autowired
    private SpringDataPostgresCustomerRepository customerRepository;

    @Override
    public int create(final Customer customer) {
        return customerRepository.save(customer).getId();
    }
}
