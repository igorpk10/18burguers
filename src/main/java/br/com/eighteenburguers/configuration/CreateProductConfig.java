package br.com.eighteenburguers.configuration;

import br.com.eighteenburguers.adapters.outbound.CreateProductAdapter;
import br.com.eighteenburguers.core.ports.inbound.product.CreateProductInputPort;
import br.com.eighteenburguers.core.usecase.product.CreateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateProductConfig {
    
    @Bean
    public CreateProductInputPort createProductUseCase(CreateProductAdapter createProductAdapter) {
        return new CreateProductUseCase(createProductAdapter);
    }
}
