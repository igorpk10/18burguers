package br.com.eighteenburguers.adapters.inbound.controller;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.eighteenburguers.adapters.inbound.controller.mappers.CustomerMapper;
import br.com.eighteenburguers.adapters.inbound.controller.request.CustomerRequest;
import br.com.eighteenburguers.adapters.inbound.controller.response.ErrorResponses;
import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.customer.CreateCustomerInputPort;
import br.com.eighteenburguers.core.ports.inbound.customer.FindCustomerInputPort;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CreateCustomerInputPort createCustomerUseCasePort;

    @Autowired
    private FindCustomerInputPort findCustomerUseCasePort;

    @Autowired
    private CustomerMapper mapper;

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

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("cpf") @CPF String cpf) {
        try {
            var customer = findCustomerUseCasePort.execute(cpf);

            if (customer != null) {
                return ResponseEntity.ok(mapper.toResponse(customer));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
