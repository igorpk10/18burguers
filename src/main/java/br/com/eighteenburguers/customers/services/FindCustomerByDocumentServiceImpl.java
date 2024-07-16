package br.com.eighteenburguers.customers.services;

import java.util.Optional;
import org.springframework.stereotype.Component;
import br.com.eighteenburguers.customers.repositories.CustomerRepository;
import br.com.eighteenburguers.customers.entitys.CustomerEntity;
import br.com.eighteenburguers.customers.mappers.CustomerEntityMapper;
import br.com.eighteenburguers.customers.entitys.Customer;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindCustomerByDocumentServiceImpl implements FindCustomerByDocumentService {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    @Override
    public Customer findByDocumentNumber(String documentNumber) {
        Optional<CustomerEntity> optional = repository.findByDocumentNumber(documentNumber);
        return optional.map(mapper::toCustomer).orElse(null);
    }
}
