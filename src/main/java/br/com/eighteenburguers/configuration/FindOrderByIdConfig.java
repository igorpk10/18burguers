package br.com.eighteenburguers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.core.ports.inbound.order.FindOrderByIdInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;
import br.com.eighteenburguers.core.usecase.order.FindOrderByIdUseCase;

@Configuration
public class FindOrderByIdConfig {
    
    @Bean
    FindOrderByIdInputPort findOrderByIdInputPort(FindOrderOutputPort findOrderOutputPort) {
        return new FindOrderByIdUseCase(findOrderOutputPort);
    }
}
