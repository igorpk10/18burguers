package br.com.eighteenburguers.core.ports.outbound.customer;

import br.com.eighteenburguers.core.domain.Customer;

public interface FindCustomerByFederalIdOutputPort {
    
    Customer findByDocumentNumber(String documentNumber);
}
