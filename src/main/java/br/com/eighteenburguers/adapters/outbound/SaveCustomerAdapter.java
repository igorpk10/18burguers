package br.com.eighteenburguers.adapters.outbound;

import br.com.eighteenburguers.adapters.outbound.repository.mapper.CustomerEntityMapper;
import br.com.eighteenburguers.adapters.outbound.repository.CustomerRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.customer.CustomerEntity;
import org.springframework.stereotype.Component;
import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.ports.outbound.customer.SaveCustomerAdapterPort;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SaveCustomerAdapter implements SaveCustomerAdapterPort {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = mapper.toEntity(customer);
        repository.save(entity);
        return mapper.toCustomer(entity);
    }
    
}
