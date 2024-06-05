package br.com.eighteenburguers.order.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.order.usecases.CreateOrderUseCase;
import br.com.eighteenburguers.order.services.SaveOrderService;
import br.com.eighteenburguers.product.services.FindProductService;
import br.com.eighteenburguers.order.usecases.CreateOrderUseCaseImpl;

@Configuration
public class CreateOrderConfig {
    
    @Bean
    CreateOrderUseCase createOrderInputPort(FindProductService findProductOutputPort, SaveOrderService saveOrderOutputPort) {
        return new CreateOrderUseCaseImpl(findProductOutputPort, saveOrderOutputPort);
    }
}
