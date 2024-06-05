package br.com.eighteenburguers.customers.controllers;

import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eighteenburguers.application.ApiV1;
import br.com.eighteenburguers.configuration.ErrorResponses;
import br.com.eighteenburguers.customers.entitys.Customer;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.customers.dtos.CustomerRequest;
import br.com.eighteenburguers.customers.mappers.CustomerMapper;
import br.com.eighteenburguers.customers.usecases.CreateCustomerUseCase;
import br.com.eighteenburguers.customers.usecases.FindCustomerUseCase;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * CustomerController
 */
@Tag(name = "Customers")
@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController implements ApiV1 {

    @Autowired
    private CreateCustomerUseCase createCustomerUseCasePort;

    @Autowired
    private FindCustomerUseCase findCustomerUseCasePort;

    @Autowired
    private CustomerMapper mapper;

    @PostMapping
    @Transactional
    @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Customer.class)))
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

            if (Objects.nonNull(customer)) {
                return ResponseEntity.status(HttpStatus.FOUND).body(mapper.toResponse(customer));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}