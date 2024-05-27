package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.exceptions.OrderNotFoundException;
import br.com.eighteenburguers.core.ports.inbound.order.FindOrderByIdInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;
import br.com.eighteenburguers.core.usecase.order.FindOrderByIdUseCase;

@ExtendWith(MockitoExtension.class)
class FindOrderByIdUseCaseTest {
    
    @Mock
    FindOrderOutputPort findOrderOutputPort;

    @Test
    void shouldBeFindById() throws BusinessException {
        Mockito.when(findOrderOutputPort.findById(Mockito.anyLong())).thenReturn(Optional.of(new Order(null, null)));
        FindOrderByIdInputPort usecase = new FindOrderByIdUseCase(findOrderOutputPort);
        assertNotNull(usecase.execute(1L));
    }

    @Test
    void shouldBeDoesNotFindById() throws BusinessException {
        Mockito.when(findOrderOutputPort.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        FindOrderByIdInputPort usecase = new FindOrderByIdUseCase(findOrderOutputPort);
        assertThrows(OrderNotFoundException.class, () -> usecase.execute(1L));
    }
}
