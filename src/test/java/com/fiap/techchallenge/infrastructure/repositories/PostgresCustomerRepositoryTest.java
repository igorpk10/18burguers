package com.fiap.techchallenge.infrastructure.repositories;

import com.fiap.techchallenge.domain.entities.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = {PostgresCustomerRepository.class, SpringDataPostgresCustomerRepository.class})
public class PostgresCustomerRepositoryTest {

    @Autowired
    PostgresCustomerRepository postgresCustomerRepository;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateShouldReturnCustomerId() {
        Customer customer = Customer.builder().build();

        int customerId = postgresCustomerRepository.create(customer);

        Assertions.assertEquals(0,customerId);
    }
}
