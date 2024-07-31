package br.com.eighteenburguers.customers.controllers;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.customers.dtos.CustomerRequest;
import br.com.eighteenburguers.customers.dtos.CustomerResponse;
import br.com.eighteenburguers.customers.mappers.CustomerMapper;
import br.com.eighteenburguers.customers.usecases.CreateCustomerUseCase;
import br.com.eighteenburguers.customers.usecases.FindCustomerUseCase;
import br.com.eighteenburguers.product.exceptions.BusinessException;

@Component
public class CustomerControllerImpl implements CustomerController{

    private CreateCustomerUseCase createCustomerUseCase;
    private FindCustomerUseCase findCustomerUseCase;

    private CustomerMapper customerMapper;

    public CustomerControllerImpl(
            CreateCustomerUseCase createCustomerUseCase,
            FindCustomerUseCase findCustomerUseCase,
            CustomerMapper customerMapper) {

        this.createCustomerUseCase = createCustomerUseCase;
        this.findCustomerUseCase = findCustomerUseCase;
        this.customerMapper = customerMapper;

    }

    public CustomerResponse createCustomer(CustomerRequest customerRequest) throws BusinessException {
        var customer = createCustomerUseCase.execute(customerMapper.toCustomer(customerRequest));
        return customerMapper.toResponse(customer);
    }

    public CustomerResponse findCustomer(String cpf) throws BusinessException {
        var customer = findCustomerUseCase.execute(cpf);        
        return customerMapper.toResponse(customer);
    }

}