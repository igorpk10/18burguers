package br.com.eighteenburguers.customers.repositories;

import java.util.Optional;

import br.com.eighteenburguers.customers.entitys.CustomerEntity;

public interface CustomerRepository {

    public Optional<CustomerEntity> findByDocumentNumber(String documentNumber);

    public CustomerEntity save(CustomerEntity customer);
}