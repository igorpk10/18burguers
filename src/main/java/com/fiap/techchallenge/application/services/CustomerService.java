package com.fiap.techchallenge.application.services;

import com.fiap.techchallenge.application.ports.infrastructure.ICustomerRepository;
import com.fiap.techchallenge.application.ports.services.ICustomerService;
import com.fiap.techchallenge.domain.entities.Customer;
import com.fiap.techchallenge.userinterface.dtos.CustomerCreateRequestData;
import com.fiap.techchallenge.userinterface.dtos.CustomerCreateResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public CustomerCreateResponseData create(CustomerCreateRequestData request) {
        Customer customer = Customer.builder()
                .email(request.getEmail())
                .cpf(request.getCpf())
                .name(request.getName())
                .build();

        int customerId = customerRepository.create(customer);

        return CustomerCreateResponseData.builder()
                .id(customerId)
                .message("Customer created successfully")
                .build();
    }
}
