package com.burguers.application.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
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
import br.com.eighteenburguers.order.exceptions.InvalidOrderStatusException;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.order.usecases.UpdateOrderStatus;
import br.com.eighteenburguers.order.usecases.UpdateOrderStatusImpl;
import br.com.eighteenburguers.product.exceptions.BusinessException;

@ExtendWith(MockitoExtension.class)
class UpdateOrderStatusImplTest {

	@Mock
	FindOrderService findOrderService;
	
	@Mock
	SaveOrderService saveOrderService;
	
	@Captor
	ArgumentCaptor<Order> captor;
	
	Faker faker;
	
	@BeforeEach
	void setup() {
		this.faker = Faker.instance();
	}
	
	@Test
	void shouldBeUpdateStatusToOrder() throws BusinessException {
		Long orderId = faker.random().nextLong();
		Order persisted = new Order(orderId, UUID.randomUUID().toString(), List.of());
		persisted.setStatus(OrderStatus.PAID);

		Mockito.when(findOrderService.findById(Mockito.anyLong())).thenReturn(Optional.of(persisted));
		
		UpdateOrderStatus usecase = new UpdateOrderStatusImpl(findOrderService, saveOrderService);
		Order order = usecase.execute(orderId, OrderStatus.IN_PREPARATION);
		
		assertEquals(orderId, order.getId());
		assertEquals(OrderStatus.IN_PREPARATION, order.getStatus());
		
		Mockito.verify(saveOrderService, Mockito.times(1)).save(captor.capture());
		
		assertEquals(orderId, captor.getValue().getId());
	}
	
	@Test
	void shouldBeNotUpdateStatusToOrder() {
		Long orderId = faker.random().nextLong();
		Order persisted = new Order(orderId, UUID.randomUUID().toString(), List.of());
		persisted.setStatus(OrderStatus.IN_PREPARATION);

		Mockito.when(findOrderService.findById(Mockito.anyLong())).thenReturn(Optional.of(persisted));
		
		UpdateOrderStatus usecase = new UpdateOrderStatusImpl(findOrderService, saveOrderService);
		assertThrows(InvalidOrderStatusException.class, () -> usecase.execute(orderId, OrderStatus.COMPLETED));
	}
	
}
