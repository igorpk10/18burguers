package com.fiap.techchallenge.application.services;

import com.fiap.techchallenge.application.ports.infrastructure.ICustomerRepository;
import com.fiap.techchallenge.application.ports.services.ICustomerService;
import com.fiap.techchallenge.domain.entities.Customer;
import com.fiap.techchallenge.presentation.dtos.CustomerCreateRequestData;
import com.fiap.techchallenge.presentation.dtos.CustomerCreateResponseData;
import com.fiap.techchallenge.presentation.dtos.CustomerGetResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public CustomerCreateResponseData create(CustomerCreateRequestData customer) {
        return new CustomerCreateResponseData();
    }

    @Override
    public CustomerGetResponseData findByCpf(String cpf) {
        Customer customer = customerRepository.findByCpf(cpf);

        return new CustomerGetResponseData();
    }
}
