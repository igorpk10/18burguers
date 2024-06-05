package br.com.eighteenburguers.customers.usecases;

import br.com.eighteenburguers.customers.entitys.Customer;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface CreateCustomerUseCase {
    
    Customer execute(Customer customer) throws BusinessException;
}
