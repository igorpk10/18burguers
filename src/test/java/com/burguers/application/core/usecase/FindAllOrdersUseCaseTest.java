package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.eighteenburguers.order.usecases.FindAllOrdersUseCase;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.usecases.FindAllOrdersUseCaseImpl;

@ExtendWith(MockitoExtension.class)
class FindAllOrdersUseCaseTest {
    
    @Mock
    FindOrderService findOrderOutputPort;

    @Test
    void shouldBeFindOrder() {
        Mockito.when(findOrderOutputPort.findAll()).thenReturn(List.of());
        FindAllOrdersUseCase usecase = new FindAllOrdersUseCaseImpl(findOrderOutputPort);
        assertNotNull(usecase.execute());
    }
}
