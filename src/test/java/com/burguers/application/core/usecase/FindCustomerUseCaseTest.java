package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.eighteenburguers.core.domain.Customer;
import br.com.eighteenburguers.core.domain.Document;
import br.com.eighteenburguers.core.domain.DocumentType;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.ports.outbound.customer.FindCustomerByFederalIdOutputPort;
import br.com.eighteenburguers.core.usecase.customer.FindCustomerUseCase;
import br.com.eighteenburguers.core.usecase.customer.exceptions.CustomerNotFound;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FindCustomerUseCaseTest {

    @Mock
    private FindCustomerByFederalIdOutputPort findByFederalIdCustomerAdapterPort;


    @Test
    void shouldReturnAUserSearchedByFederalId() throws BusinessException{
        var findCustomerUseCase = new FindCustomerUseCase(findByFederalIdCustomerAdapterPort);
        var documentNumber = "80632725010";
        Document document = new Document(DocumentType.CPF, documentNumber);
        Customer customer = new Customer(document, "Luffy", "luffy@reidospiratas.com");


        when(findByFederalIdCustomerAdapterPort.findByDocumentNumber(documentNumber)).thenReturn(customer);

        var customerReturned = findCustomerUseCase.execute(documentNumber);
        assertNotNull(customerReturned);
        assertEquals(customerReturned.getDocument().getNumber(), documentNumber);
    }

    @Test
    void shouldReturnNotFoundWhenUserDoesNotExists() throws BusinessException{
        var findCustomerUseCase = new FindCustomerUseCase(findByFederalIdCustomerAdapterPort);
        var documentNumber = "80632725010";
        
        when(findByFederalIdCustomerAdapterPort.findByDocumentNumber(documentNumber)).thenReturn(null);

        assertThrows(CustomerNotFound.class,() -> findCustomerUseCase.execute(documentNumber));
    }
    
}
