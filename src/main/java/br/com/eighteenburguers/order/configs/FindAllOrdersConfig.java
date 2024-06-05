package br.com.eighteenburguers.order.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.eighteenburguers.order.usecases.FindAllOrdersUseCase;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.usecases.FindAllOrdersUseCaseImpl;

@Component
public class FindAllOrdersConfig {
    
    @Bean
    FindAllOrdersUseCase findAllOrdersInputPort(FindOrderService findOrderOutputPort) {
        return new FindAllOrdersUseCaseImpl(findOrderOutputPort);
    }
}
