package com.fiap.techchallenge.userinterface.controllers;

import com.fiap.techchallenge.application.exceptions.CustomerConflictException;
import com.fiap.techchallenge.application.ports.services.ICustomerService;
import com.fiap.techchallenge.userinterface.dtos.CustomerCreateResponseData;
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
    void testPostShouldReturnCustomerId() throws Exception {
        CustomerCreateResponseData response = CustomerCreateResponseData.builder().build();
        Mockito.when(customerService.create(Mockito.any())).thenReturn(response);

        this.mockMvc.perform(
                post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                )
                .andExpect(status().isCreated());
    }

    @Test
    void testPostShouldReturnConflict() throws Exception {
        Mockito.when(customerService.create(Mockito.any())).thenThrow(CustomerConflictException.class);

        this.mockMvc.perform(
                        post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isConflict());
    }
}
