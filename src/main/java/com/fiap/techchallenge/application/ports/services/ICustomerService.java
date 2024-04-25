package com.fiap.techchallenge.application.ports.services;

import com.fiap.techchallenge.userinterface.dtos.CustomerCreateRequestData;
import com.fiap.techchallenge.userinterface.dtos.CustomerCreateResponseData;

public interface ICustomerService {
    CustomerCreateResponseData create(CustomerCreateRequestData customer);
}
