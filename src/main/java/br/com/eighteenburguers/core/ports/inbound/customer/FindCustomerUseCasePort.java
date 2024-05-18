package br.com.eighteenburguers.core.ports.inbound.customer;

import java.util.Optional;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.exceptions.BusinessException;

public interface FindCustomerUseCasePort {

    Customer execute(String id) throws BusinessException;
}
