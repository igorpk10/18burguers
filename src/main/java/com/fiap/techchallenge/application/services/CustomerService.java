package com.fiap.techchallenge.application.services;

import com.fiap.techchallenge.application.ports.infrastructure.ICustomerRepository;
import com.fiap.techchallenge.application.ports.services.ICustomerService;
import com.fiap.techchallenge.domain.entities.Customer;
import com.fiap.techchallenge.presentation.dtos.CustomerCreateRequestData;
import com.fiap.techchallenge.presentation.dtos.CustomerCreateResponseData;
import com.fiap.techchallenge.presentation.dtos.CustomerGetResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

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

    @Override
    public CustomerGetResponseData findByCpf(String cpf) {
        Optional<Customer> response = customerRepository.findByCpf(cpf);

        if (response.isEmpty()) {
            return null;
        }

        Customer customer = response.get();

        return CustomerGetResponseData.builder()
                .id(customer.getId())
                .cpf(customer.getCpf())
                .email(customer.getEmail())
                .name(customer.getName())
                .build();
    }
}
