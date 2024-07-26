package com.burguers.application.core.usecase;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.PaymentService;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.order.usecases.PaymentUpdateUseCase;
import br.com.eighteenburguers.order.usecases.PaymentUpdateUseCaseImpl;
import br.com.eighteenburguers.order.valueobjects.Payment;
import br.com.eighteenburguers.order.valueobjects.PaymentStatus;
import br.com.eighteenburguers.product.exceptions.BusinessException;

@ExtendWith(MockitoExtension.class)
class PaymentUpdateUseCaseImplTest {

	@Mock
    SaveOrderService saveOrderOutputPort;
    @Mock
	FindOrderService findOrderOutputPort;
    @Mock
    PaymentService paymentService;

    Faker faker;
    
    @BeforeEach
    void setup() {
    	this.faker = Faker.instance();
    }
    
	@Test
	void shouldBeUpdatePaymentStatus() throws BusinessException {
		final Long orderId = faker.random().nextLong();
		final String customerId = UUID.randomUUID().toString();
		
		Mockito.when(findOrderOutputPort.findById(Mockito.anyLong())).thenReturn(Optional.of(new Order(customerId, List.of())));
		Mockito.when(paymentService.check(Mockito.anyLong())).thenReturn(new Payment(orderId, PaymentStatus.COMPLETED, Instant.now()));
		
		PaymentUpdateUseCase usecase = new PaymentUpdateUseCaseImpl(saveOrderOutputPort, findOrderOutputPort, paymentService);
		usecase.execute(orderId);
		
		Mockito.verify(saveOrderOutputPort, Mockito.times(1)).save(Mockito.any());
	}
}
