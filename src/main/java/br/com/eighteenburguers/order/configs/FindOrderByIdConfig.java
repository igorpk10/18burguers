package br.com.eighteenburguers.order.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.order.usecases.FindOrderByIdUseCase;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.usecases.FindOrderByIdUseCaseImpl;

@Configuration
public class FindOrderByIdConfig {
    
    @Bean
    FindOrderByIdUseCase findOrderByIdInputPort(FindOrderService findOrderOutputPort) {
        return new FindOrderByIdUseCaseImpl(findOrderOutputPort);
    }
}
