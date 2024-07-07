package br.com.eighteenburguers.customers.controllers;

import br.com.eighteenburguers.customers.dtos.CustomerRequest;
import br.com.eighteenburguers.customers.dtos.CustomerResponse;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface CustomerController {
     public CustomerResponse createCustomer(CustomerRequest customerRequest) throws BusinessException;

     public CustomerResponse findCustomer(String cpf) throws BusinessException;
}
