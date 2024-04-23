package com.fiap.techchallenge.infrastructure.repositories;

import com.fiap.techchallenge.domain.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataPostgresCustomerRepository extends CrudRepository<Customer, Integer>{
}
