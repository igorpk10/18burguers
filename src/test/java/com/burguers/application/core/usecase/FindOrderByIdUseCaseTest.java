package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.exceptions.OrderNotFoundException;
import br.com.eighteenburguers.order.usecases.FindOrderByIdUseCase;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.usecases.FindOrderByIdUseCaseImpl;

@ExtendWith(MockitoExtension.class)
class FindOrderByIdUseCaseTest {
    
    @Mock
    FindOrderService findOrderOutputPort;

    @Test
    void shouldBeFindById() throws BusinessException {
        Mockito.when(findOrderOutputPort.findById(Mockito.anyLong())).thenReturn(Optional.of(new Order(UUID.randomUUID().toString(), List.of())));
        FindOrderByIdUseCase usecase = new FindOrderByIdUseCaseImpl(findOrderOutputPort);
        assertNotNull(usecase.execute(1L));
    }

    @Test
    void shouldBeDoesNotFindById() throws BusinessException {
        Mockito.when(findOrderOutputPort.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        FindOrderByIdUseCase usecase = new FindOrderByIdUseCaseImpl(findOrderOutputPort);
        assertThrows(OrderNotFoundException.class, () -> usecase.execute(1L));
    }
}
