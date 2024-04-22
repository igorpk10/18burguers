package com.fiap.techchallenge.presentation.controllers;

import com.fiap.techchallenge.application.ports.services.ICustomerService;
import com.fiap.techchallenge.presentation.dtos.CustomerResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponse> get(@PathVariable(name = "cpf") String cpf) {
        return ResponseEntity
                .ok(new CustomerResponse());
    }
}
