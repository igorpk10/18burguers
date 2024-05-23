package br.com.eighteenburguers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.eighteenburguers.core.ports.inbound.order.FindAllOrdersInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;
import br.com.eighteenburguers.core.usecase.order.FindAllOrdersUseCase;

@Component
public class FindAllOrdersConfig {
    
    @Bean
    FindAllOrdersInputPort findAllOrdersInputPort(FindOrderOutputPort findOrderOutputPort) {
        return new FindAllOrdersUseCase(findOrderOutputPort);
    }
}
