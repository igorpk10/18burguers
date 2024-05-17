package br.com.eighteenburguers.adapters.outbound;

import java.util.Optional;

import br.com.eighteenburguers.adapters.outbound.repository.mapper.CustomerEntityMapper;
import br.com.eighteenburguers.adapters.outbound.repository.CustomerRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.customer.CustomerEntity;
import org.springframework.stereotype.Component;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.ports.outbound.customer.FindByFederalIdCustomerAdapterPort;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindCustomerAdapter implements FindByFederalIdCustomerAdapterPort {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    @Override
    public Customer findByDocumentNumber(String documentNumber) {
        Optional<CustomerEntity> optional = repository.findByDocumentNumber(documentNumber);
        if(optional.isEmpty()) return null;
        return mapper.toCustomer(optional.get());
    }
}
