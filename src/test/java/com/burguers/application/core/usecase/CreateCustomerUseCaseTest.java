package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.domain.Document;
import br.com.eighteenburguers.core.domain.DocumentType;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.inbound.customer.CreateCustomerUseCasePort;
import br.com.eighteenburguers.core.ports.outbound.customer.FindCustomerAdapterPort;
import br.com.eighteenburguers.core.ports.outbound.customer.SaveCustomerAdapterPort;
import br.com.eighteenburguers.core.usecase.customer.CreateCustomerUseCase;
import br.com.eighteenburguers.core.usecase.customer.CustomerAlreadyExistsException;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {
    
    @Mock
    FindCustomerAdapterPort findCustomerAdapterPort;
    
    @Mock
    SaveCustomerAdapterPort saveCustomerAdapterPort;

    Faker faker;

    @BeforeAll
    void setup() {
        this.faker = Faker.instance();
    }

    @Test
    void shouldBeAbleToCreateANewCustomer() throws BusinessException {
        Document document = new Document(DocumentType.CPF,"80632725010");
        Customer customer = new Customer(document, faker.name().fullName() , faker.internet().emailAddress());

        when(findCustomerAdapterPort.findByDocumentNumber(anyString())).thenReturn(Optional.empty());
        when(saveCustomerAdapterPort.save(any())).thenReturn(customer);

        CreateCustomerUseCasePort usecase = new CreateCustomerUseCase(findCustomerAdapterPort, saveCustomerAdapterPort);
        Customer response = usecase.execute(customer);

        assertNotNull(response);

        verify(findCustomerAdapterPort, times(1)).findByDocumentNumber(anyString());
        verify(saveCustomerAdapterPort, times(1)).save(any());
    }

    @Test
    void shouldBeNotAbleToCreateANewCustomerBecauseAlredyExists() {

        Document document = new Document(DocumentType.CPF,"80632725010");
        Customer customer = new Customer(document, faker.name().fullName() , faker.internet().emailAddress());

        when(findCustomerAdapterPort.findByDocumentNumber(anyString())).thenReturn(Optional.of(customer));

        CreateCustomerUseCasePort usecase = new CreateCustomerUseCase(findCustomerAdapterPort, saveCustomerAdapterPort);
        assertThrows(CustomerAlreadyExistsException.class, () -> usecase.execute(customer));

        verify(findCustomerAdapterPort, times(1)).findByDocumentNumber(anyString());
        verify(saveCustomerAdapterPort, times(0)).save(any());
    }
}
