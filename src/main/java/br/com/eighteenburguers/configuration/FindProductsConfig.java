package br.com.eighteenburguers.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.core.ports.inbound.product.FindProductsInputPort;
import br.com.eighteenburguers.core.ports.outbound.product.FindProductOutputPort;
import br.com.eighteenburguers.core.usecase.product.FindProductsUseCase;

@Configuration
public class FindProductsConfig {
    
    @Bean
    FindProductsInputPort findProductsInputPort(FindProductOutputPort findProductOutputPort) {
        return new FindProductsUseCase(findProductOutputPort);
    }
}
