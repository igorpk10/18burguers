package br.com.eighteenburguers.adapters.outbound.repository.customer;

import org.springframework.stereotype.Component;
import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.ports.outbound.SaveCustomerAdapterPort;
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
