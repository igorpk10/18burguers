package br.com.eighteenburguers.order.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.order.usecases.CheckoutOrderUseCase;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.order.usecases.CheckoutOrderUseCaseImpl;

@Configuration
public class CheckoutOrderConfig {

    @Bean
    CheckoutOrderUseCase checkoutOrderUseCase(SaveOrderService saveOrderOutputPort, FindOrderService findOrderOutputPort) {
        return new CheckoutOrderUseCaseImpl(saveOrderOutputPort, findOrderOutputPort);
    }
}
