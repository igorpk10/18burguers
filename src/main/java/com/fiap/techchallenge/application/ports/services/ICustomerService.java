package com.fiap.techchallenge.application.ports.services;

import com.fiap.techchallenge.presentation.dtos.CustomerCreateRequestData;
import com.fiap.techchallenge.presentation.dtos.CustomerCreateResponseData;
import com.fiap.techchallenge.presentation.dtos.CustomerGetResponseData;

public interface ICustomerService {
    CustomerCreateResponseData create(CustomerCreateRequestData customer);
    CustomerGetResponseData findByCpf(String cpf);
}
