package com.fiap.techchallenge.application.services;

import com.fiap.techchallenge.application.ports.infrastructure.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerService {
    @Autowired
    ICustomerRepository customerRepository;
}
