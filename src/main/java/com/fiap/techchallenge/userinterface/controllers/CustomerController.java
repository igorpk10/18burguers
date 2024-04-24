package com.fiap.techchallenge.userinterface.controllers;

import com.fiap.techchallenge.application.exceptions.CustomerConflictException;
import com.fiap.techchallenge.application.ports.services.ICustomerService;
import com.fiap.techchallenge.userinterface.dtos.CustomerCreateRequestData;
import com.fiap.techchallenge.userinterface.dtos.CustomerCreateResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerCreateResponseData> create(@RequestBody CustomerCreateRequestData request) {
        try {
            CustomerCreateResponseData response = customerService.create(request);
            return ResponseEntity.created(URI.create("/customer/" + response.getId())).body(response);
        } catch (CustomerConflictException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
