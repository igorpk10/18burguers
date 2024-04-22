package com.fiap.techchallenge.presentation.controllers;

import com.fiap.techchallenge.application.ports.services.ICustomerService;
import com.fiap.techchallenge.presentation.dtos.CustomerCreateResponseData;
import com.fiap.techchallenge.presentation.dtos.CustomerGetResponseData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICustomerService customerService;

    @Test
    void testGetShouldReturnCustomer() throws Exception {
        Mockito.when(customerService.findByCpf(Mockito.anyString())).thenReturn(new CustomerGetResponseData());

        this.mockMvc.perform(get("/customers/123"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetShouldReturnNotFound() throws Exception {
        Mockito.when(customerService.findByCpf(Mockito.anyString())).thenReturn(null);

        this.mockMvc.perform(get("/customers/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testPostShouldReturnCustomerId() throws Exception {
        CustomerCreateResponseData response = new CustomerCreateResponseData();
        Mockito.when(customerService.create(Mockito.any())).thenReturn(response);

        this.mockMvc.perform(
                post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                )
                .andExpect(status().isCreated());
    }
}
