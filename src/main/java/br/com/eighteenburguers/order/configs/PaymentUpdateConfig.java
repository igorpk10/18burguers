package br.com.eighteenburguers.order.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.PaymentService;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.order.usecases.PaymentUpdateUseCase;
import br.com.eighteenburguers.order.usecases.PaymentUpdateUseCaseImpl;

@Configuration
public class PaymentUpdateConfig {

	@Bean
	PaymentUpdateUseCase paymentUpdateUseCase(SaveOrderService saveOrderOutputPort, FindOrderService findOrderOutputPort, PaymentService paymentService) {
		return new PaymentUpdateUseCaseImpl(saveOrderOutputPort, findOrderOutputPort, paymentService);
	}
}
