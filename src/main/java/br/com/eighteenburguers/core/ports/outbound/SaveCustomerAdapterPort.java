package br.com.eighteenburguers.core.ports.outbound;

import br.com.eighteenburguers.core.domain.Customer;

public interface SaveCustomerAdapterPort {
    
    Customer save(Customer customer);
}