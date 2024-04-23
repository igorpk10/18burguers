package com.fiap.techchallenge.presentation.controllers;

import com.fiap.techchallenge.application.ports.services.ICustomerService;
import com.fiap.techchallenge.presentation.dtos.CustomerCreateRequestData;
import com.fiap.techchallenge.presentation.dtos.CustomerCreateResponseData;
import com.fiap.techchallenge.presentation.dtos.CustomerGetResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerCreateResponseData> create(@RequestBody CustomerCreateRequestData request) {
        CustomerCreateResponseData response = customerService.create(request);

        return ResponseEntity.created(URI.create("/customer/" + response.getId())).body(response);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerGetResponseData> get(@PathVariable(name = "cpf") String cpf) {
        CustomerGetResponseData response = customerService.findByCpf(cpf);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok(response);
    }
}
