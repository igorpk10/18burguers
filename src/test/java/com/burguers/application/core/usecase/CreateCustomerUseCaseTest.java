package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.github.javafaker.Faker;
import br.com.eighteenburguers.customers.entitys.Customer;
import br.com.eighteenburguers.customers.entitys.Document;
import br.com.eighteenburguers.customers.entitys.DocumentType;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.customers.services.FindCustomerByDocumentService;
import br.com.eighteenburguers.customers.services.SaveCustomerService;
import br.com.eighteenburguers.customers.usecases.CreateCustomerUseCaseImpl;
import br.com.eighteenburguers.customers.exceptions.CustomerAlreadyExistsException;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {

    @Mock
    FindCustomerByDocumentService findCustomerAdapterPort;

    @Mock
    SaveCustomerService saveCustomerAdapterPort;

    Faker faker;

    @BeforeAll
    void setup() {
        this.faker = Faker.instance();
    }

    @Test
    void shouldBeAbleToCreateANewCustomer() throws BusinessException {
        var documentNumber = "80632725010";
        Document document = new Document(DocumentType.CPF, documentNumber);
        Customer customer = new Customer(document, faker.name().fullName(), faker.internet().emailAddress());

        when(findCustomerAdapterPort.findByDocumentNumber(anyString())).thenReturn(null);
        when(saveCustomerAdapterPort.save(any())).thenReturn(customer);

        CreateCustomerUseCaseImpl usecase = new CreateCustomerUseCaseImpl(findCustomerAdapterPort, saveCustomerAdapterPort);
        Customer response = usecase.execute(customer);

        assertNotNull(response);

        verify(findCustomerAdapterPort, times(1)).findByDocumentNumber(anyString());
        verify(saveCustomerAdapterPort, times(1)).save(any());
    }

    @Test
    void shouldBeNotAbleToCreateANewCustomerBecauseAlreadyExists() {
        var documentNumber = "80632725010";
        Document document = new Document(DocumentType.CPF, documentNumber);
        Customer customer = new Customer(document, faker.name().fullName(), faker.internet().emailAddress());

        when(findCustomerAdapterPort.findByDocumentNumber(anyString())).thenReturn(new Customer(
            new Document(null, documentNumber), null, null
        ));

        CreateCustomerUseCaseImpl usecase = new CreateCustomerUseCaseImpl(findCustomerAdapterPort, saveCustomerAdapterPort);
        assertThrows(CustomerAlreadyExistsException.class, () -> usecase.execute(customer));

        verify(findCustomerAdapterPort, times(1)).findByDocumentNumber(anyString());
        verify(saveCustomerAdapterPort, times(0)).save(any());
    }
}
