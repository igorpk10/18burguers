package br.com.eighteenburguers.core.ports.outbound.customer;

import java.util.Optional;

import br.com.eighteenburguers.core.domain.Customer;

public interface FindCustomerAdapterPort {
    
    Optional<Customer> findById(Long id);

    Optional<Customer> findByDocumentNumber(String documentNumber);
}
