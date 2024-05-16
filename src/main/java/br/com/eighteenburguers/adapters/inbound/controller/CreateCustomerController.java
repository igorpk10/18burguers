package br.com.eighteenburguers.adapters.inbound.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eighteenburguers.adapters.inbound.controller.mappers.CustomerMapper;
import br.com.eighteenburguers.adapters.inbound.controller.request.CustomerRequest;
import br.com.eighteenburguers.adapters.inbound.controller.response.ErrorResponses;
import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.customer.CreateCustomerUseCasePort;
import br.com.eighteenburguers.core.ports.outbound.customer.FindCustomerAdapterPort;
import br.com.eighteenburguers.core.ports.outbound.customer.SaveCustomerAdapterPort;
import br.com.eighteenburguers.core.usecase.customer.CreateCustomerUseCase;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CreateCustomerController {

    private final CreateCustomerUseCasePort createCustomerUseCasePort;
    private final CustomerMapper mapper;

    public CreateCustomerController(FindCustomerAdapterPort findCustomerAdapterPort,
            SaveCustomerAdapterPort saveCustomerAdapterPort, CustomerMapper mapper) {
        this.createCustomerUseCasePort = new CreateCustomerUseCase(findCustomerAdapterPort, saveCustomerAdapterPort);
        this.mapper = mapper;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid CustomerRequest request) {
        try {
            Customer customer = createCustomerUseCasePort.execute(mapper.toCustomer(request));
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(customer));
        } catch (BusinessException e) {
            log.error("Error when trying to create customer: {}: {}", e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponses(e));
        }
    }
}
