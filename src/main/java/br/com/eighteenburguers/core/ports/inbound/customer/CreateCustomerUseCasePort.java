package br.com.eighteenburguers.core.ports.inbound.customer;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.exceptions.BusinessException;

public interface CreateCustomerUseCasePort {
    
    Customer execute(Customer customer) throws BusinessException;
}
