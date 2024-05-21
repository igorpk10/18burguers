package br.com.eighteenburguers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.core.ports.inbound.order.CreateOrderInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.SaveOrderOutputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductOutputPort;
import br.com.eighteenburguers.core.usecase.order.CreateOrderUseCase;

@Configuration
public class CreateOrderConfig {
    
    @Bean
    CreateOrderInputPort createOrderInputPort(FindProductOutputPort findProductOutputPort, SaveOrderOutputPort saveOrderOutputPort) {
        return new CreateOrderUseCase(findProductOutputPort, saveOrderOutputPort);
    }
}
