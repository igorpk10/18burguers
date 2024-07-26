package br.com.eighteenburguers.customers.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.customers.datasources.CustomerDataSource;
import br.com.eighteenburguers.customers.entitys.CustomerEntity;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private CustomerDataSource customerDataSource;

    public CustomerRepositoryImpl(CustomerDataSource customerDataSource) {
        this.customerDataSource = customerDataSource;
    }

    @Override
    public Optional<CustomerEntity> findByDocumentNumber(String documentNumber) {
        Optional<CustomerEntity> optional = customerDataSource.findByDocumentNumber(documentNumber);
        return optional;
    }

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) {
        CustomerEntity saved = customerDataSource.save(customerEntity);
        return saved;
    }
    
}
