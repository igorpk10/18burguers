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

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderStatus;
import br.com.eighteenburguers.product.exceptions.OrderNotFoundException;
import br.com.eighteenburguers.order.usecases.CheckoutOrderUseCase;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.order.usecases.CheckoutOrderUseCaseImpl;

@ExtendWith(MockitoExtension.class)
class CheckoutOrderUseCaseTest {
    
    @Mock
    SaveOrderService saveOrderOutputPort;
    
    @Mock
    FindOrderService findOrderOutputPort;

    @Captor
    ArgumentCaptor<Order> captor;

    @Test
    void shouldBeCheckoutOrder(){
        Mockito.when(findOrderOutputPort.findById(Mockito.anyLong())).thenReturn(Optional.of(mockOrder()));
        CheckoutOrderUseCase usecase = new CheckoutOrderUseCaseImpl(saveOrderOutputPort, findOrderOutputPort);
        assertDoesNotThrow(() -> usecase.execute(Faker.instance().random().nextLong()));

        Mockito.verify(findOrderOutputPort, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(saveOrderOutputPort, Mockito.times(1)).save(captor.capture());
        assertEquals(OrderStatus.PAID, captor.getValue().getStatus());
    }

    @Test
    void shouldBeNotCheckoutBecauseDoesNotExists() {
        Mockito.when(findOrderOutputPort.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        CheckoutOrderUseCase usecase = new CheckoutOrderUseCaseImpl(saveOrderOutputPort, findOrderOutputPort);
        assertThrows(OrderNotFoundException.class, () -> usecase.execute(Faker.instance().random().nextLong()));

        Mockito.verify(findOrderOutputPort, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(saveOrderOutputPort, Mockito.times(0)).save(captor.capture());
    }

    Order mockOrder() {
        return new Order(1L, List.of());
    }
}
