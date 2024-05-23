package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.eighteenburguers.core.ports.inbound.order.FindAllOrdersInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;
import br.com.eighteenburguers.core.usecase.order.FindAllOrdersUseCase;

@ExtendWith(MockitoExtension.class)
class FindAllOrdersUseCaseTest {
    
    @Mock
    FindOrderOutputPort findOrderOutputPort;

    @Test
    void shouldBeFindOrder() {
        Mockito.when(findOrderOutputPort.findAll()).thenReturn(List.of());
        FindAllOrdersInputPort usecase = new FindAllOrdersUseCase(findOrderOutputPort);
        assertNotNull(usecase.execute());
    }
}
