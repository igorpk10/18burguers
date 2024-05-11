package br.com.eighteenburguers.adapters.outbound.repository.customer;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.ports.outbound.customer.FindCustomerAdapterPort;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindCustomerAdapter implements FindCustomerAdapterPort {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    @Override
    public Optional<Customer> findById(Long id) {
        Optional<CustomerEntity> optional = repository.findById(id);
        if(optional.isEmpty()) return Optional.empty();
        return Optional.of(mapper.toCustomer(optional.get()));
    }

    @Override
    public Optional<Customer> findByDocumentNumber(String documentNumber) {
        Optional<CustomerEntity> optional = repository.findByDocumentNumber(documentNumber);
        if(optional.isEmpty()) return Optional.empty();
        return Optional.of(mapper.toCustomer(optional.get()));
    }

}
