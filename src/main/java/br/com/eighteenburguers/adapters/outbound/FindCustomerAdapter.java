package br.com.eighteenburguers.adapters.outbound;

import java.util.Optional;
import org.springframework.stereotype.Component;
import br.com.eighteenburguers.adapters.outbound.repository.CustomerRepository;
import br.com.eighteenburguers.adapters.outbound.repository.entity.customer.CustomerEntity;
import br.com.eighteenburguers.adapters.outbound.repository.mapper.CustomerEntityMapper;
import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.ports.outbound.customer.FindCustomerByFederalIdOutputPort;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindCustomerAdapter implements FindCustomerByFederalIdOutputPort {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    @Override
    public Customer findByDocumentNumber(String documentNumber) {
        Optional<CustomerEntity> optional = repository.findByDocumentNumber(documentNumber);
        return optional.map(mapper::toCustomer).orElse(null);
    }
}
