package com.fiap.techchallenge.presentation.controllers;

import com.fiap.techchallenge.application.ports.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerController {
    @Autowired
    ICustomerService customerService;
}
