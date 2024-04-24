package com.fiap.techchallenge.application.services;

import com.fiap.techchallenge.application.ports.infrastructure.ICustomerRepository;
import com.fiap.techchallenge.userinterface.dtos.CustomerCreateRequestData;
import com.fiap.techchallenge.userinterface.dtos.CustomerCreateResponseData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CustomerServiceTest {

    @Mock
    ICustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateShouldReturnCustomerId() {
        int expect = 1;

        Mockito.when(customerRepository.create(Mockito.any())).thenReturn(expect);

        CustomerCreateRequestData customerCreateRequestData = CustomerCreateRequestData.builder().build();

        CustomerCreateResponseData customerCreateResponseData = customerService.create(customerCreateRequestData);

        Assertions.assertEquals(expect, customerCreateResponseData.getId());
    }
}
