package br.com.eighteenburguers.customers.usecases;

import br.com.eighteenburguers.customers.entitys.Customer;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface FindCustomerUseCase {

    Customer execute(String id) throws BusinessException;
}
