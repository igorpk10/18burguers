package br.com.eighteenburguers.product.configs;

import br.com.eighteenburguers.product.usecase.FindProductsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.product.usecase.FindProductsUseCase;
import br.com.eighteenburguers.product.services.FindProductService;

@Configuration
public class FindProductsConfig {
    
    @Bean
    FindProductsUseCase findProductsInputPort(FindProductService findProductOutputPort) {
        return new FindProductsUseCaseImpl(findProductOutputPort);
    }
}
