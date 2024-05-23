package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.domain.OrderStatus;
import br.com.eighteenburguers.core.exceptions.OrderNotFoundException;
import br.com.eighteenburguers.core.ports.inbound.order.CheckoutOrderInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;
import br.com.eighteenburguers.core.ports.outbound.order.SaveOrderOutputPort;
import br.com.eighteenburguers.core.usecase.order.CheckoutOrderUseCase;

@ExtendWith(MockitoExtension.class)
class CheckoutOrderUseCaseTest {
    
    @Mock
    SaveOrderOutputPort saveOrderOutputPort;
    
    @Mock
    FindOrderOutputPort findOrderOutputPort;

    @Captor
    ArgumentCaptor<Order> captor;

    @Test
    void shouldBeCheckoutOrder(){
        Mockito.when(findOrderOutputPort.findById(Mockito.anyLong())).thenReturn(Optional.of(mockOrder()));
        CheckoutOrderInputPort usecase = new CheckoutOrderUseCase(saveOrderOutputPort, findOrderOutputPort);
        assertDoesNotThrow(() -> usecase.execute(Faker.instance().random().nextLong()));

        Mockito.verify(findOrderOutputPort, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(saveOrderOutputPort, Mockito.times(1)).save(captor.capture());
        assertEquals(OrderStatus.PAID, captor.getValue().getStatus());
    }

    @Test
    void shouldBeNotCheckoutBecauseDoesNotExists() {
        Mockito.when(findOrderOutputPort.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        CheckoutOrderInputPort usecase = new CheckoutOrderUseCase(saveOrderOutputPort, findOrderOutputPort);
        assertThrows(OrderNotFoundException.class, () -> usecase.execute(Faker.instance().random().nextLong()));

        Mockito.verify(findOrderOutputPort, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(saveOrderOutputPort, Mockito.times(0)).save(captor.capture());
    }

    Order mockOrder() {
        return new Order(1L, List.of());
    }
}
