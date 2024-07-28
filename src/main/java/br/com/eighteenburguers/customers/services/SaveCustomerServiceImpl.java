package br.com.eighteenburguers.customers.services;

import org.springframework.stereotype.Component;
import br.com.eighteenburguers.customers.repositories.CustomerRepository;
import br.com.eighteenburguers.customers.entitys.CustomerEntity;
import br.com.eighteenburguers.customers.mappers.CustomerEntityMapper;
import br.com.eighteenburguers.customers.entitys.Customer;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SaveCustomerServiceImpl implements SaveCustomerService {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        var saved = repository.save(entity);
        return mapper.toCustomer(saved);
    }
    
}
